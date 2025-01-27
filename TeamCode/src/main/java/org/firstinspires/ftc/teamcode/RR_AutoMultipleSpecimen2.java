package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "2 RR Autonomous Multiple Specimen(MIDDLE)")
public class RR_AutoMultipleSpecimen2 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Servo specimenServo = hardwareMap.servo.get("specimenArm");


        TrajectoryActionBuilder action2 = drive.actionBuilder(new Pose2d(0,0,0))
                // specimen scoring servo position
                .stopAndAdd(new ServoAction(specimenServo, 0.25))
                .setTangent(0)
                // spline to submersible
                .splineToConstantHeading(new Vector2d(50, 24), Math.toRadians(0))
                // put arm down
                .stopAndAdd(new ServoAction(specimenServo, 0.738))
                // move back
                .lineToX(24)

                // strafe to the right
                .setTangent(Math.PI / 2)
                .lineToY(-29)

                // wait a few seconds
                .waitSeconds(2)

                // move back
                .setTangent(0)
                .lineToX(5)

                // wait a few seconds
                .waitSeconds(2)

                // specimen scoring servo position
                .stopAndAdd(new ServoAction(specimenServo, 0.25))

                // spline to submersible
                .splineToConstantHeading(new Vector2d(40, 24), Math.toRadians(0))

                .lineToX(50)

                // put arm down
                .stopAndAdd(new ServoAction(specimenServo, 0.738))
                // move back
                .lineToX(24);

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        action2.build()
                )
        );
    }

    // class for positioning the servo arm
    public class ServoAction implements Action {
        Servo servo;
        double position;

        public ServoAction(Servo initialServo, double initialPosition) {
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