package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto Move Samples", group = "Concept")

// will score the preset block and park in observation zone
public class AutoMoveSamples extends LinearOpMode {

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
            roboController.moveOnYAxis(50, 0.8);

            // move all the way right
            roboController.moveOnXAxis(10, 0.5);

            // move back
            roboController.moveOnYAxis(-48, 0.8);

            // move forward
            roboController.moveOnYAxis(48, 0.8);

            // move all the way right
            roboController.moveOnXAxis(10, 0.5);

            // move back
            roboController.moveOnYAxis(-48, 0.8);

            // move forward
            roboController.moveOnYAxis(48, 0.8);

            // move all the way right
            roboController.moveOnXAxis(10, 0.5);

            // move back
            roboController.moveOnYAxis(-48, 0.8);

            // move forward
            roboController.moveOnYAxis(30, 0.8);

            // wait for 15 seconds for human player to attach clips onto samples
            sleep(15000);

            // move back and park (kinda undershot it so it wouldn't slam against the wall)
            roboController.moveOnYAxis(-25, 0.6);
        }
    }
}