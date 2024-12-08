package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "LEFT - Auto MAYBE", group = "Concept")

// will score the preset block and park in observation zone
public class AutoMAYBE extends LinearOpMode {

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

            // autonomous scoring towards the buckets but diff

            // presetting arm
            // neutral position
            roboController.shoulder.setPosition(0.125);

            sleep(500);

            // preset tilt bucket up
            roboController.outClaw.setPosition(0);

            // move right slightly
            roboController.moveOnXAxis(6,0.5);

            // turn left slightly
            roboController.Spin(-4, 0.5);

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

            // turn right slightly
            roboController.Spin(4, 0.5);

            // move right
            roboController.moveOnXAxis(24, 0.5);

            // move all the way back
            roboController.moveOnYAxis(-96, 0.8);

            // drop off position
            roboController.shoulder.setPosition(0.32);

            // move left
            roboController.moveOnXAxis(-24, 0.8);
        }
    }
}