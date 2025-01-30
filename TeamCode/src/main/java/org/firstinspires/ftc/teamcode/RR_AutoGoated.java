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

@Autonomous(name = "3 RR Autonomous Goated (MIDDLE)")
public class RR_AutoGoated extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // initial position of robot
        Pose2d initialPose = new Pose2d(12,-72,Math.PI / 2);
        // robot to be used
        MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
        // creation of specimen arm object to use
        Servo specimenServo = hardwareMap.servo.get("specimenArm");


        // 1st action - scoring one specimen
        TrajectoryActionBuilder action1 = drive.actionBuilder(initialPose)
                // specimen scoring servo position
                .stopAndAdd(new ServoAction(specimenServo, 0.25))
                //.setTangent(0)

                // 1
                // spline to submersible
                .splineToConstantHeading(new Vector2d(0, -26), 0)
                // put arm down
                .stopAndAdd(new ServoAction(specimenServo, 0.738))
                // move back
                .lineToY(-48);

        // 2nd action - unique spline to samples on floor
        TrajectoryActionBuilder action2 = drive.actionBuilder(new Pose2d(0.00, -48.00, Math.PI / 2))
                .splineToConstantHeading(new Vector2d(36.20, -29.93), Math.PI / 2)
                .splineToConstantHeading(new Vector2d(37.96, -15.65), Math.PI / 2)
                .splineToConstantHeading(new Vector2d(47.35, -8.41), Math.PI / 2);


        // 3rd action - moving one sample that's on the back into the zone (will add more later)
        TrajectoryActionBuilder action3 = drive.actionBuilder(new Pose2d(47.35, -8.41, Math.PI / 2))
                // flip specimen arm forward
                .stopAndAdd(new ServoAction(specimenServo, 0.25))

                // move block back
                .lineToY(-65);

                /*
                // 2
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
                .splineToConstantHeading(new Vector2d(40, 23), Math.toRadians(0))

                .lineToX(50)

                // put arm down
                .stopAndAdd(new ServoAction(specimenServo, 0.738))
                // move back
                .lineToX(24)

                // 3
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
                .splineToConstantHeading(new Vector2d(40, 22), Math.toRadians(0))

                .lineToX(50)

                // put arm down
                .stopAndAdd(new ServoAction(specimenServo, 0.738))
                // move back
                .lineToX(24);
                 */


        waitForStart();

        // connecting all three actions together so the robot runs them back to back
        Actions.runBlocking(
                new SequentialAction(
                        action1.build(),
                        action2.build(),
                        action3.build()
                )
        );
    }

    // class for positioning the servo arm
    public class ServoAction implements Action {
        Servo servo;
        double position;

        public ServoAction(Servo usedServo, double finalPosition) {
            this.servo = usedServo;
            this.position = finalPosition;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            servo.setPosition(position);

            return false;
        }
    }
}