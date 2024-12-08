package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "LEFT - Auto Less Park", group = "Concept")

// will score the preset block and park in observation zone
public class AutoParkLessLEFT extends LinearOpMode {

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
            // drop off position
            roboController.shoulder.setPosition(0.32);

            sleep(500);

            // preset tilt bucket up
            roboController.outClaw.setPosition(0);

            // move forward
            roboController.moveOnYAxis(24, 0.8);

            // move all the way right
            roboController.moveOnXAxis(72, 0.8);

            // move back
            roboController.moveOnYAxis(-24, 0.8);
        }
    }
}