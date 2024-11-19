package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class SoloDrive extends LinearOpMode {
    // ** add private variables/robo parts later
    // placeholder robo parts for now

    // wheel parts
    // (F = Front, B = Back, L = Left, R = Right, W = Wheel)
    private DcMotor FLW;
    private DcMotor FRW;
    private DcMotor BLW;
    private DcMotor BRW;

    // arm parts
    private DcMotor VLS; // linear slide for outtake
    private DcMotor HLS; // linear slide for intake
    private Servo outClaw; // bucket
    private Servo shoulder; // lower part closest to linear slide
    private Servo elbow; // upper part
    private Servo inClaw; //servo closest to white inclaw

    // button logic variables
    private boolean inClawLastState;
    private boolean outClawLastState;
    private int inArmState;
    private boolean inArmLastState;

    @Override
    public void runOpMode() {
        // wheel config
        FLW = hardwareMap.get(DcMotor.class, "FLW");
        FRW = hardwareMap.get(DcMotor.class, "FRW");
        BLW = hardwareMap.get(DcMotor.class, "BLW");
        BRW = hardwareMap.get(DcMotor.class, "BRW");

        // intake arm config
        HLS = hardwareMap.get(DcMotor.class,"HLS");
        shoulder = hardwareMap.get(Servo.class,"shoulder");
        elbow = hardwareMap.get(Servo.class,"elbow");
        inClaw = hardwareMap.get(Servo.class,"inClaw");

        //outtake arm config
        VLS = hardwareMap.get(DcMotor.class,"VLS");
        outClaw = hardwareMap.get(Servo.class,"outClaw");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        inClawLastState = false;
        outClawLastState = false;

        inArmState = -1;
        inArmLastState = false;

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // while opMode is running, allow the methods for manipulating the wheels and arms
            // to be used and print data values
            moveRobot(gamepad1);

            telemetry.addData("FLW Motor Power", FLW.getPower());
            telemetry.addData("FRW Motor Power", FRW.getPower());
            telemetry.addData("BLW Motor Power", BLW.getPower());
            telemetry.addData("BRW Motor Power", BRW.getPower());

            telemetry.addData("Outtake Arm Motor Power", VLS.getPower());
            telemetry.addData("Intake Arm Motor Power", HLS.getPower());
            telemetry.addData("Outtake Claw Servo Position", outClaw.getPosition());
            telemetry.addData("Intake Claw Servo Position", inClaw.getPosition());
            telemetry.addData("Shoulder Servo Position", shoulder.getPosition());
            telemetry.addData("Elbow Servo Position", elbow.getPosition());

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }

    public void moveRobot(Gamepad gamepad){
        // ** the functions below could be for any bot, depending on what controls you want to use to drive the bot **
        double drivePower = 0;
        double strafePower = 0;
        double turnPower = 0;

        FRW.setDirection(DcMotorSimple.Direction.REVERSE);
        BRW.setDirection(DcMotorSimple.Direction.REVERSE);

        // moves the robot's (wheel) motors forward and back using the game pad 1 left joystick
        drivePower = -gamepad.left_stick_y;

        // moves the robot's (wheel) motors left and right using the game pad 1 left joystick
        strafePower = gamepad.left_stick_x;

        // turns the robot's (wheel) motors left and right using the game pad 1 right joystick
        turnPower = gamepad.right_stick_x;

        // drive, turn, and strafe logic
        // https://youtu.be/jRVUHapKx4o?si=1jVJ-ts7d2rkHCdq
        FLW.setPower(drivePower + turnPower + strafePower);
        FRW.setPower(drivePower - turnPower - strafePower);
        BLW.setPower(drivePower + turnPower - strafePower);
        BRW.setPower(drivePower - turnPower + strafePower);


        telemetry.addData("Drive Power", drivePower);
        telemetry.addData("Strafe Power", strafePower);
        telemetry.addData("Turn Power", turnPower);


        // ** the functions below are for the polymorphism bot **
        // presets servo positions

        // powers the robot's (arm) motors using the game pad 2 left joystick
        // (this will make the arm's motor power vary depending on how much you're using the joystick.
        // this can be changed later if we want the motor to have a constant power no matter what
        // value the joystick is giving)
        // linear slide(s) for outtake arm
        // (provides a threshold for deactivating motor power since joystick may not be at exactly 0)
        if(gamepad.right_trigger > 0.25) {
            HLS.setPower(-gamepad.right_trigger);
        } else if(gamepad.left_trigger > 0.25){
            HLS.setPower(gamepad.left_trigger);
        } else{
            HLS.setPower(0);
        }

        if(gamepad.right_bumper){
            VLS.setPower(1);
        } else if(gamepad.left_bumper) {
            VLS.setPower(-1);
        } else {
            VLS.setPower(0);
        }

        if(gamepad.circle && !inArmLastState){
            if(inArmState == 2){
                inArmState = 0;
            } else {
                inArmState++;
            }

            if(inArmState == 0){
                // neutral position
                shoulder.setPosition(0.2);
                elbow.setPosition(0.6);
            } else if(inArmState == 1){
                // dropoff position
                shoulder.setPosition(0.32);
                elbow.setPosition(0.2);
            } else if(inArmState == 2){
                // pickup position
                shoulder.setPosition(0.8);
                elbow.setPosition(0.3);
            }
        }

        inArmLastState = gamepad.circle;

        // 1 = open
        // 0 = closed
        if(gamepad.triangle && !inClawLastState) {
            if (inClaw.getPosition() <= 0.6) {
                inClaw.setPosition(1);
            }else{
                inClaw.setPosition(0.2);
            }
        }
        inClawLastState = gamepad.triangle;


        if(gamepad.x && !outClawLastState){
            if (outClaw.getPosition() < 0.5) {
                outClaw.setPosition(1);
            }else{
                outClaw.setPosition(0);
            }
        }

        outClawLastState = gamepad.x;

        if(gamepad.square){
            outClaw.setPosition(0.5);
        }
    }
}