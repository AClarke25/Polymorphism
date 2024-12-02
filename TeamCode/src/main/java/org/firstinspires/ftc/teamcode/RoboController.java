package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RoboController {
    private LinearOpMode opMode;

    // wheel parts
    // (F = Front, B = Back, L = Left, R = Right, W = Wheel)
    public DcMotor FLW;
    public DcMotor FRW;
    public DcMotor BLW;
    public DcMotor BRW;

    // arm parts
    public DcMotor VLS; // linear slide for outtake
    public DcMotor HLS; // linear slide for intake
    public Servo outClaw; // bucket
    public Servo shoulder; // lower part closest to linear slide
    public Servo elbow; // upper part
    public Servo inClaw; //servo closest to white inclaw

    // button logic variables
    public boolean inClawLastState;
    public boolean outClawLastState;
    public int inArmState;
    public boolean inArmLastState;

    public RoboController(LinearOpMode opMode){

        this.opMode = opMode;
        HardwareMap hardwareMap = opMode.hardwareMap;

        // wheel config
        FLW = hardwareMap.get(DcMotor.class, "FLW");
        FRW = hardwareMap.get(DcMotor.class, "FRW");
        BLW = hardwareMap.get(DcMotor.class, "BLW");
        BRW = hardwareMap.get(DcMotor.class, "BRW");

        // intake arm config
        HLS = hardwareMap.get(DcMotor.class,"HLS");
        shoulder = hardwareMap.get(Servo.class,"shoulder");
        elbow = hardwareMap.get(Servo.class,"elbow");
        inClaw = hardwareMap.get(Servo.class,"inClaw");

        //outtake arm config
        VLS = hardwareMap.get(DcMotor.class,"VLS");
        outClaw = hardwareMap.get(Servo.class,"outClaw");

        inClawLastState = false;
        outClawLastState = false;

        inArmState = -1;
        inArmLastState = false;

        // presetting
        FRW.setDirection(DcMotorSimple.Direction.REVERSE);
        BRW.setDirection(DcMotorSimple.Direction.REVERSE);

        FLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BLW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BRW.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
