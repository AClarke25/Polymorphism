package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

//@Autonomous(name = "Auto Score Two Specimen", group = "Concept")

// will score the preset block and park in observation zone
public class AutoScoreTwoSpecimen extends LinearOpMode {

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

            // autonomous score two specimen

            /*
            // preset drop off position
            roboController.shoulder.setPosition(0.32);

            // set specimenArm position 2
            roboController.specimenArm.setPosition(0.25);

            sleep(1000);

            // move left
            roboController.moveOnXAxis(-12, 0.8);

            // move forward to submersible (over shot to ensure we're against it)
            roboController.moveOnYAxis(38, 0.45);

            // set specimenArm position 1
            roboController.specimenArm.setPosition(0.738);

            sleep(1000);

            // move back to attach specimen
            roboController.moveOnYAxis(-12, 0.9);

            // turn slightly to the right
            roboController.Spin(1,0.5);

            // move right
            roboController.moveOnXAxis(48, 0.8);

            sleep(1000);

            // move back to get specimen
            roboController.moveOnYAxis(-17, 0.8);

            // set specimenArm position 2
            roboController.specimenArm.setPosition(0.25);

            sleep(1000);

            // move forward
            roboController.moveOnYAxis(17, 0.8);

            // move left
            roboController.moveOnXAxis(-48, 0.8);

            // move forward
            roboController.moveOnYAxis(20, 0.45);

            // set specimenArm position 1
            roboController.specimenArm.setPosition(0.738);

            sleep(1000);

            // move back to attach specimen
            roboController.moveOnYAxis(-12, 0.9);


            /*
            // move backward
            roboController.moveOnYAxis(-45, 0.6);

            // move left and park
            roboController.moveOnXAxis(-28, 0.6);
             */
        }
    }
}