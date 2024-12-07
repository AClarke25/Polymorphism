package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class TwoPersonDrive extends LinearOpMode {
    private RoboController roboController;

    @Override
    public void runOpMode() {
        roboController = new RoboController(this);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // while opMode is running, allow the methods for manipulating the wheels and arms
            // to be used and print data values
            moveWheels(gamepad1);
            moveArm(gamepad2);

            telemetry.addData("FLW Motor Power", roboController.FLW.getPower());
            telemetry.addData("FRW Motor Power", roboController.FRW.getPower());
            telemetry.addData("BLW Motor Power", roboController.BLW.getPower());
            telemetry.addData("BRW Motor Power", roboController.BRW.getPower());

            telemetry.addData("Outtake Arm Motor Power", roboController.VLS.getPower());
            telemetry.addData("Intake Arm Motor Power", roboController.HLS.getPower());
            telemetry.addData("Outtake Claw Servo Position", roboController.outClaw.getPosition());
            telemetry.addData("Intake Claw Servo Position", roboController.inClaw.getPosition());
            telemetry.addData("Shoulder Servo Position", roboController.shoulder.getPosition());
            telemetry.addData("Wrist Servo Position", roboController.wrist.getPosition());

            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }

    public void moveWheels(Gamepad movepad){
        // ** wheel movement **
        double drivePower = 0;
        double strafePower = 0;
        double turnPower = 0;

        // moves the robot's (wheel) motors forward and back using the game pad 1 left joystick
        drivePower = -movepad.left_stick_y;

        // moves the robot's (wheel) motors left and right using the game pad 1 left joystick
        strafePower = movepad.left_stick_x;

        // turns the robot's (wheel) motors left and right using the game pad 1 right joystick
        turnPower = movepad.right_stick_x;

        // drive, turn, and strafe logic
        // https://youtu.be/jRVUHapKx4o?si=1jVJ-ts7d2rkHCdq

        roboController.FLW.setPower(drivePower + turnPower + strafePower);
        roboController.FRW.setPower(drivePower - turnPower - strafePower);
        roboController.BLW.setPower(drivePower + turnPower - strafePower);
        roboController.BRW.setPower(drivePower - turnPower + strafePower);


        telemetry.addData("Drive Power", drivePower);
        telemetry.addData("Strafe Power", strafePower);
        telemetry.addData("Turn Power", turnPower);
    }

    public void moveArm(Gamepad armpad){
        // ** arm movement **

        boolean clawStartedOpen = false;

        // powers the robot's (arm) motors using the game pad 2 left joystick
        // (this will make the arm's motor power vary depending on how much you're using the joystick.
        // this can be changed later if we want the motor to have a constant power no matter what
        // value the joystick is giving)
        // linear slide(s) for outtake arm
        // (provides a threshold for deactivating motor power since joystick may not be at exactly 0)

        // right = extend
        // left = retract

        // triggers control extension of intake arm
        if(armpad.right_trigger > 0.25) {
            roboController.HLS.setPower(armpad.right_trigger);
        } else if(armpad.left_trigger > 0.25){
            roboController.HLS.setPower(-armpad.left_trigger);
        } else{
            roboController.HLS.setPower(0);
        }

        // bumpers control extension of outtake arm
        if(armpad.left_bumper){
            roboController.VLS.setPower(-1);
        } else if(armpad.right_bumper) {
            roboController.VLS.setPower(1);
        } else {
            roboController.VLS.setPower(0);
        }

        // circle controls 3 intake arm positions
        if(armpad.circle && !roboController.inArmLastState){
            if(roboController.inArmState == 2){
                roboController.inArmState = 0;
            } else {
                roboController.inArmState++;
            }

            if(roboController.inArmState == 0){
                // neutral position
                roboController.shoulder.setPosition(0);

            } else if(roboController.inArmState == 1){
                // pickup position (slightly hovered)
                roboController.shoulder.setPosition(0.64);

                /*
                // pickup position (slightly hovered)
                roboController.shoulder.setPosition(0.65);

                // 0.65 = open, 0.5 = closed
                if(roboController.inClaw.getPosition() == 0.65){
                    clawStartedOpen = true;
                } else if(roboController.inClaw.getPosition() == 0.5){
                    clawStartedOpen = false;
                }
                */

            } else if(roboController.inArmState == 2){
                // drop off position
                roboController.shoulder.setPosition(0.32);
            }
        }

        if(armpad.dpad_up || armpad.dpad_down || armpad.dpad_left || armpad.dpad_right){
            if(!roboController.inArmLastStateLower){
                if(roboController.inArmState == 1) {
                    // pickup position (on block level)
                    roboController.shoulder.setPosition(0.73);
                }

                roboController.inArmLastStateLower = true;
            }
        } else {
            if(roboController.inArmState == 1) {
                // pickup position (slightly hovered)
                roboController.shoulder.setPosition(0.64);
            }

            roboController.inArmLastStateLower = false;
        }

        roboController.inArmLastState = armpad.circle;

        //roboController.inArmLastStateLower = (armpad.dpad_up || armpad.dpad_down || armpad.dpad_left || armpad.dpad_right);

        // 1 = open
        // 0 = closed

        // x/a controls opening and closing claw
        if(armpad.a && !roboController.inClawLastState) {
            // initially closed
            if (roboController.inClaw.getPosition() <= 0.575) {
                // opening
                roboController.inClaw.setPosition(0.65);

                // initially opened
            } else {
                // closing
                roboController.inClaw.setPosition(0.4);
            }

            /*
            // initially closed
            if (roboController.inClaw.getPosition() <= 0.575) {
                // opening
                roboController.inClaw.setPosition(0.65);

                // if in pickup position
                if(roboController.inArmState == 1){
                    // wait a bit to ensure the arm is down
                    sleep(300);

                    // make the arm hover a bit
                    roboController.shoulder.setPosition(0.65);
                }

            // initially opened
            } else {
                // closing
                roboController.inClaw.setPosition(0.5);

                // if in pickup position
                if (roboController.inArmState == 1) {
                    // wait a bit to ensure the arm is down
                    sleep(500);

                    // make the arm actually go to the floor
                    roboController.shoulder.setPosition(0.77);
                }
w
                if(clawStartedOpen){
                    // if in pickup position
                    if(roboController.inArmState == 1){
                        // wait a bit to ensure the arm is down
                        sleep(500);

                        // make the arm actually go to the floor
                        roboController.shoulder.setPosition(0.77);
                    }

                    // closing
                    roboController.inClaw.setPosition(0.5);
                } else {
                    // closing
                    roboController.inClaw.setPosition(0.5);

                    // if in pickup position
                    if (roboController.inArmState == 1) {
                        // wait a bit to ensure the arm is down
                        sleep(500);

                        // make the arm actually go to the floor
                        roboController.shoulder.setPosition(0.77);
                    }
                }
            }
            */
        }

        roboController.inClawLastState = armpad.a;

        // triangle controls the bucket position
        if(armpad.triangle && !roboController.outClawLastState){
            if (roboController.outClaw.getPosition() < 0.5) {
                roboController.outClaw.setPosition(1);
            }else{
                roboController.outClaw.setPosition(0);
            }
        }

        roboController.outClawLastState = armpad.triangle;

        // square controls adjustment of wrist position
        if(armpad.square && !roboController.wristLastState){
            if (roboController.wrist.getPosition() < 0.25) {
                roboController.wrist.setPosition(0.5);
            }else{
                roboController.wrist.setPosition(0);
            }
        }

        roboController.wristLastState = armpad.square;
    }
}