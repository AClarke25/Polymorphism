package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Auto Score and Park", group = "Concept")

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
            // move forward
            roboController.moveOnYAxis(24, 1);
            
            // rotate left 135 degrees
            roboController.Spin(-27,1);

            // move forward
            roboController.moveOnYAxis(15, 1);

            // extend arm up
            roboController.VLS.setPower(1);

            sleep(2000);

            roboController.VLS.setPower(0);

            // maybe move forward a bit to adjust

            // tilt bucket down
            roboController.outClaw.setPosition(1);

            sleep(1000);

            // tilt bucket up
            roboController.outClaw.setPosition(0);

            // adjust in opposite way if needed

            // retract arm
            roboController.VLS.setPower(-1);

            sleep(2000);

            roboController.VLS.setPower(0);

            // move back
            roboController.moveOnYAxis(-15, 1);

            // rotate right 45 degrees
            roboController.Spin(9,1);

            // move back
            roboController.moveOnYAxis(-96,1);

            // move left and park in observation zone
            roboController.moveOnXAxis(-24,1);


            // test
            /*
            roboController.moveOnYAxis(24, 1);

            sleep(2000);

            roboController.moveOnYAxis(24, 1);

            sleep(2000);

            roboController.moveOnXAxis(48, 1);
             */
        }
    }
}