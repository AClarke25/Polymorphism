package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto Move Samples LEFT", group = "Concept")

// will score the preset block and park in observation zone
public class AutoMoveSamplesLEFT extends LinearOpMode {

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

            // 1
            // move forward
            roboController.moveOnYAxis(50, 0.8);

            // move left
            roboController.moveOnXAxis(-10, 0.5);

            // move back
            roboController.moveOnYAxis(-48, 0.8);

            // 2
            // move forward
            roboController.moveOnYAxis(48, 0.8);

            // move left
            roboController.moveOnXAxis(-10, 0.5);

            // move back
            roboController.moveOnYAxis(-48, 0.8);
        }
    }
}