package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import java.lang.Math;


public class AutoScoreAndPark {
    public void moveOnXAxis(int ticks) {
        opMode.telemetry.addData("x", "");
        opMode.telemetry.update();
        DcMotor frontLeft = FLW,
                frontRight = FRW,
                rearLeft = BLW,
                rearRight = BRW;

        // Reset encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setTargetPosition(ticks);
        rearLeft.setTargetPosition(-ticks);
        frontRight.setTargetPosition(-ticks);
        rearRight.setTargetPosition(ticks);





        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(1);
        frontRight.setPower(-1);
        rearLeft.setPower(-1);
        rearRight.setPower(1);


        while (opMode.opModeIsActive() && frontLeft.isBusy()) {
            // Loop until the motor reaches its target position.
            opMode.telemetry.addData("Front Left Encoder", frontLeft.getCurrentPosition());
            opMode.telemetry.addData("Front Right Encoder", frontRight.getCurrentPosition());
            opMode.telemetry.addData("Rear Left Encoder", rearLeft.getCurrentPosition());
            opMode.telemetry.addData("Rear Right Encoder", rearRight.getCurrentPosition());
            opMode.telemetry.update();
        }

        opMode.sleep(250);
    }
}
