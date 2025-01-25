package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "LEFT - Auto Score and Park (BACKUP PROB DON'T USE)", group = "Concept")

// will score the preset block and park in observation zone
public class AutoScoreAndPark extends LinearOpMode {

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

            // presetting arm
            // neutral position
            roboController.shoulder.setPosition(0.125);

            sleep(500);

            // preset tilt bucket up
            roboController.outClaw.setPosition(0);

            // move forward
            roboController.moveOnYAxis(26, 0.85);

            // rotate left 135 degrees
            roboController.Spin(-24.5,0.7);

            // move forward
            roboController.moveOnYAxis(12, 0.85);

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
            roboController.moveOnYAxis(-12, 0.85);

            // drop off position
            roboController.shoulder.setPosition(0.32);

            // **** change speeds later
            // rotate right 45 degrees
            roboController.Spin(9,0.85);

            // move back
            roboController.moveOnYAxis(-95,0.85);

            // move left and park in observation zone
            roboController.moveOnXAxis(-25,0.85);
        }
    }
}