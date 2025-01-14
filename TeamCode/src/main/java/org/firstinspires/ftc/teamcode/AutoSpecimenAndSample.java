package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto Specimen And Sample", group = "Concept")

// will score the preset block and park in observation zone
public class AutoSpecimenAndSample extends LinearOpMode {

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

            // autonomous score specimen and push one sample into zone

            // preset drop off position
            roboController.shoulder.setPosition(0.32);

            // set specimenArm position 2
            roboController.specimenArm.setPosition(0.25);

            sleep(1000);

            // move left
            roboController.moveOnXAxis(-12, 0.8);

            // move forward to submersible (over shot to ensure we're against it)
            roboController.moveOnYAxis(38, 0.4);

            // set specimenArm position 1
            roboController.specimenArm.setPosition(0.738);

            sleep(1000);

            // move back to attach specimen
            roboController.moveOnYAxis(-12, 0.9);

            // turn slightly to the right
            roboController.Spin(1,0.5);

            // move right
            roboController.moveOnXAxis(43, 0.8);

            // turn slightly to the right
            roboController.Spin(2,0.5);

            // set specimenArm position 2
            roboController.specimenArm.setPosition(0.25);

            sleep(1000);

            // move forward
            roboController.moveOnYAxis(27, 0.8);

            // move right
            roboController.moveOnXAxis(10, 0.6);

            // move backward
            roboController.moveOnYAxis(-42, 0.7);

            // move forward for human player to attach clip
            roboController.moveOnYAxis(10, 0.5);

            // wait for human player
            sleep(4000);

            // move back and park
            roboController.moveOnYAxis(-10, 0.5);
        }
    }
}