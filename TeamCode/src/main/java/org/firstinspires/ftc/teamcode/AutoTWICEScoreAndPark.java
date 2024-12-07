package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "LEFT - Auto TWICE Score and Park", group = "Concept")

// will score the preset block and park in observation zone
public class AutoTWICEScoreAndPark extends LinearOpMode {

    @Override
    public void runOpMode() {
        RoboController roboController = new RoboController(this);

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            // 18 inches = about 90 degree turn
            // Diagonal distance = about 33.94113

            // autonomous scoring towards the buckets

            // **** presetting arm
            // neutral bucket position
            roboController.shoulder.setPosition(0);

            // opened claw
            roboController.inClaw.setPosition(0.4);

            // rotated wrist
            roboController.wrist.setPosition(0.5);

            sleep(500);

            // preset tilt bucket up
            roboController.outClaw.setPosition(0);

            // **** actual movement
            // move forward
            roboController.moveOnYAxis(24, 0.9);

            // rotate left 135 degrees
            roboController.Spin(-25,0.8);

            // move forward
            roboController.moveOnYAxis(12, 0.9);

            // extend arm up
            roboController.VLS.setPower(-1);

            sleep(4500);

            roboController.VLS.setPower(0);

            // maybe move forward a bit to adjust

            // tilt bucket down
            roboController.outClaw.setPosition(1);

            sleep(1000);

            // tilt bucket up
            roboController.outClaw.setPosition(0);

            // adjust in opposite way if needed

            // retract arm
            roboController.VLS.setPower(1);

            sleep(3700);

            roboController.VLS.setPower(0);

            // move back
            roboController.moveOnYAxis(-12, 0.9);


            // rotate right 45 degrees
            roboController.Spin(9,0.9);

            // **** new stuff
            // move right
            roboController.moveOnXAxis(6, 0.8);

            // extend arm out
            roboController.HLS.setPower(-0.5);

            sleep(1000);

            roboController.HLS.setPower(0);

            // pickup position (on block level)
            roboController.shoulder.setPosition(0.73);

            // ensure its down
            sleep(400);

            // closed claw
            roboController.inClaw.setPosition(0.65);

            // ensure its closed
            sleep(400);

            // drop off position
            roboController.shoulder.setPosition(0.32);

            // retract arm in
            roboController.HLS.setPower(0.5);

            sleep(1000);

            roboController.HLS.setPower(0);

            // opened claw
            roboController.inClaw.setPosition(0.4);

            // neutral position
            roboController.shoulder.setPosition(0);

            // move left
            roboController.moveOnXAxis(-6, 0.7);

            // rotate left 45 degrees
            roboController.Spin(-9,0.85);

            // **** initial main movement again
            // move forward
            roboController.moveOnYAxis(12, 0.85);

            // extend arm up
            roboController.VLS.setPower(-1);

            sleep(4500);

            roboController.VLS.setPower(0);

            // tilt bucket down
            roboController.outClaw.setPosition(1);

            sleep(1000);

            // tilt bucket up
            roboController.outClaw.setPosition(0);

            // retract arm
            roboController.VLS.setPower(1);

            sleep(3700);

            roboController.VLS.setPower(0);

            // move back
            roboController.moveOnYAxis(-12, 0.85);

            // rotate right 45 degrees
            roboController.Spin(9,0.85);


            // **** go park
            // move back
            roboController.moveOnYAxis(-95,0.85);

            // drop off position
            roboController.shoulder.setPosition(0.32);

            // move left and park in observation zone
            roboController.moveOnXAxis(-25,0.85);

            /*
            // test
            roboController.moveOnYAxis(24, 1);

            sleep(2000);

            roboController.moveOnYAxis(24, 1);

            sleep(2000);

            roboController.moveOnXAxis(48, 1);
            */
        }
    }
}