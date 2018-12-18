package org.firstinspires.ftc.teamcode;
// Put this in part of teamcode folder.

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@Disabled
@Autonomous(name = "BlueC", group = "linear Opmode")                     // Register your Opmode. On the RC the Opmode will show up as the name string.

/* Extend the LinearOpMode.*/
public class BlueC extends LinearOpMode {
    DcMotor F1, F2, R1, R2, A1;
    double heading, heading_degrees, power, leftstick_xsq, leftstick_ysq;
    double bias, powerF1, powerF2, powerR1, powerR2;
    double position = 0.5;

    public void runOpMode()/* This begins the initialization process fro the robot (after driver presses "INIT" on DS */ {
        F1 = hardwareMap.get(DcMotor.class, "f1");// Initialize the motor variable for F1.  Named f1 in the RC phone.
        F1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        F2 = hardwareMap.get(DcMotor.class, "f2");                      // Initialize the motor variable for F2.  Named f2 in the RC phone.
        F2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R1 = hardwareMap.get(DcMotor.class, "r1");                      // Initialize the motor variable for R1.  Named r1 in the RC phone.
        R1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        R2 = hardwareMap.get(DcMotor.class, "r2");                      // Initialize the motor variable for R2.  Named r2 in the RC phone.
        R2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        A1 = hardwareMap.get(DcMotor.class, "a1");
        A1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        waitForStart();                                              // Wait for the game to start (driver presses PLAY).
        F1.setPower(-1);
        F2.setPower(1);
        R1.setPower(-1);
        R2.setPower(1);
        sleep(3000);
        F1.setPower(0);
        F2.setPower(0);
        R1.setPower(0);
        R2.setPower(0);


    }
}