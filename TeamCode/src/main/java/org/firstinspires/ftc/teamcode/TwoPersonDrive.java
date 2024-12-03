/*
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

// annotation used to differentiate TeleOp and Auto modes
// (i guess it just mainly changes the tab it appears in on the controller??)
@TeleOp
public class TwoPersonDrive extends LinearOpMode {
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
            moveWheels(gamepad1);
            moveArm(gamepad2);

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

    public void moveWheels(Gamepad movepad){
        // ** the functions below could be for any bot, depending on what controls you want to use to drive the bot **
        double drivePower = 0;
        double strafePower = 0;
        double turnPower = 0;

        FRW.setDirection(DcMotorSimple.Direction.REVERSE);
        BRW.setDirection(DcMotorSimple.Direction.REVERSE);

        // moves the robot's (wheel) motors forward and back using the game pad 1 left joystick
        drivePower = -movepad.left_stick_y;

        // moves the robot's (wheel) motors left and right using the game pad 1 left joystick
        strafePower = movepad.left_stick_x;

        // turns the robot's (wheel) motors left and right using the game pad 1 right joystick
        turnPower = movepad.right_stick_x;

        // drive, turn, and strafe logic
        // https://youtu.be/jRVUHapKx4o?si=1jVJ-ts7d2rkHCdq
        FLW.setPower(drivePower + turnPower + strafePower);
        FRW.setPower(drivePower - turnPower - strafePower);
        BLW.setPower(drivePower + turnPower - strafePower);
        BRW.setPower(drivePower - turnPower + strafePower);


        telemetry.addData("Drive Power", drivePower);
        telemetry.addData("Strafe Power", strafePower);
        telemetry.addData("Turn Power", turnPower);

        /* vv things i tried but couldn't figure out vv

        boolean motorsPowered = false;

        // basically, if none of the game pad 1 joysticks are being used, this means the robot's
        // wheels shouldn't be moving, meaning those motors should return to a power of 0
        if(drivePower == 0 && strafePower == 0 && turnPower == 0){
            motorsPowered = false;
        } else {
            motorsPowered = true;
        }

        // gives different power values to the motors (depending on the action being done) if the
        // joysticks are being used
        if(motorsPowered){
            // driving and strafing
            if(drivePower != 0 && strafePower != 0){
                // im not exactly sure if this will work, i just copied it from last year's code
                FRW.setPower(-strafePower);
                FLW.setPower(drivePower);
                BRW.setPower(drivePower);
                BLW.setPower(-strafePower);
                // just driving
            } if(drivePower != 0){
                FLW.setPower(drivePower);
                FRW.setPower(drivePower);
                BLW.setPower(drivePower);
                BRW.setPower(drivePower);
            }
        } else {
            FLW.setPower(0);
            FRW.setPower(0);
            BLW.setPower(0);
            BRW.setPower(0);
        }
        */
/*
    }

    public void moveArm(Gamepad armpad){
        // ** the functions below are for the polymorphism bot **
        // presets servo positions
//        outClaw.setPosition(0);
//        elbow.setPosition(0);
//        shoulder.setPosition(0);

        // powers the robot's (arm) motors using the game pad 2 left joystick
        // (this will make the arm's motor power vary depending on how much you're using the joystick.
        // this can be changed later if we want the motor to have a constant power no matter what
        // value the joystick is giving)
        // linear slide(s) for outtake arm
        // (provides a threshold for deactivating motor power since joystick may not be at exactly 0)
        if(armpad.right_trigger > 0.25) {
            HLS.setPower(-armpad.right_trigger);
        } else if(armpad.left_trigger > 0.25){
            HLS.setPower(armpad.left_trigger);
        } else{
            HLS.setPower(0);
        }

        if(armpad.right_bumper){
            VLS.setPower(1);
        } else if(armpad.left_bumper){
            VLS.setPower(-1);
        } else{
            VLS.setPower(0);
        }


        if(armpad.circle && !inArmLastState){
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

        inArmLastState = armpad.circle;

        // 1 = open
        // 0 = closed
        if(armpad.triangle && !inClawLastState) {
            if (inClaw.getPosition() <= 0.6) {
                inClaw.setPosition(1);
            }else{
                inClaw.setPosition(0.2);
            }
        }
        inClawLastState = armpad.triangle;


        if(armpad.x && !outClawLastState){
            if (outClaw.getPosition() < 0.5) {
                outClaw.setPosition(1);
            }else{
                outClaw.setPosition(0);
            }
        }

        outClawLastState = armpad.x;

        if(armpad.square){
            outClaw.setPosition(0.5);
        }

        // same logic for intake and outtake linear slides
//        if(armpad.right_stick_y > 0.25 || armpad.right_stick_y < -0.25) {
//            HLS.setPower(-armpad.left_stick_y);
//        } else {
//            HLS.setPower(0);
//        }

        // circle = neutral position
        // triangle = dropoff position
        // square = pickup position

        // shoulder = servo attached to HLS
        // elbow = servo in between HLS and claw
        /*
        if(armpad.circle) {
            shoulder.setPosition(0.2);
            elbow.setPosition(0.6);
        } else if(armpad.triangle) {
            shoulder.setPosition(0.32);
            elbow.setPosition(0.2);
        } else if(armpad.square){
            shoulder.setPosition(0.8);
            elbow.setPosition(0.3);
        }

         */

        /*
        if((armpad.dpad_up || armpad.dpad_down) && !outClawLastState){
            if (outClaw.getPosition() < 0.5) {
                outClaw.setPosition(1);
            }else{
                outClaw.setPosition(0);
            }
        }


        outClawLastState = (armpad.dpad_up || armpad.dpad_down);

        if(armpad.dpad_left || armpad.dpad_right){
            outClaw.setPosition(0.5);
        }
         */

        // uses the gamepad's X button as a switch to tilt and un-tilt the outtake's "claw's" bucket
        // (1 = tilt, 0 = not tilt)
//        if(armpad.x) {
//            if(OutClaw.getPosition() < 0.5) {
//                OutClaw.setPosition(1);
//            } else {
//                OutClaw.setPosition(0);
//            }
//        }
//
//        // uses the gamepad's circle button as a switch to open and unopen the intake's claw
//        // (1 = open, 0 = closed)
//        if(armpad.circle) {
//            if(Wrist.getPosition() < 0.5) {
//                Wrist.setPosition(1);
//            } else {
//                Wrist.setPosition(0);
//            }
//        }
//
//        // pressing the triangle to trigger whatever actions will happen to hang/climb
//        if(armpad.triangle) {
//            // trigger hanging maneuver
//        }
/*
    }
}
*/