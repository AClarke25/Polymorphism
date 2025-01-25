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

@Autonomous(name = "RR Autonomous Multiple Specimen (MIDDLE)")
public class RR_AutoMultipleSpecimen extends LinearOpMode {
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
                .stopAndAdd(new ServoAction(specimenServo, 0.25))
                // turn right
                .turn(Math.toRadians(-90))
                .setTangent(Math.PI / 2)
                // move forward
                .lineToY(-26)
                // turn left
                .turn(Math.toRadians(90))
                .setTangent(0)
                // 1
                // move forward
                .lineToX(66)
                // move right in front of specimen
                .setTangent(Math.PI / 2)
                .strafeTo(new Vector2d(66, -44))
                // push specimen back
                .setTangent(0)
                .lineToX(3)
                // 2
                // move forward
                .lineToX(66)
                // move right in front of specimen
                .setTangent(Math.PI / 2)
                .strafeTo(new Vector2d(66, -58))
                // push specimen back
                .setTangent(0)
                .lineToX(3)
                // 3
                // move forward
                .lineToX(66)
                // move right in front of specimen
                .setTangent(Math.PI / 2)
                .strafeTo(new Vector2d(66, -72))
                // push specimen back
                .setTangent(0)
                .lineToX(3)
                // more forward outside of zone
                .lineToX(20)
                // wait until clip is put on
                .waitSeconds(3)
                // move back and park
                .lineToX(1);

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
