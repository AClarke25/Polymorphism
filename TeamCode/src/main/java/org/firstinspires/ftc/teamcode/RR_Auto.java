package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "RR Autonomous")
public class RR_Auto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Servo specimenServo = hardwareMap.servo.get("specimenArm");

        waitForStart();

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))
                        // specimen scoring servo position
                        .stopAndAdd(new ServoAction(specimenServo, 0.25))
                        .setTangent(0)
                        // spline to submersible
                        .splineToConstantHeading(new Vector2d(30, 24), Math.toRadians(0))
                        // put arm down
                        .stopAndAdd(new ServoAction(specimenServo, 0.738))
                        .build());
    }

    public class ServoAction implements Action {
        Servo servo;
        double position;

        public ServoAction(Servo initialServo, double initialPosition){
            this.servo = initialServo;
            this.position = initialPosition;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            servo.setPosition(position);

            return false;
        }
    }
}
