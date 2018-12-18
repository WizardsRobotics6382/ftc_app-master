package org.firstinspires.ftc.teamcode;
 // Put this in part of teamcode folder.

        import android.app.Activity;
        import android.graphics.Color;
        import android.view.View;

        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
        import com.qualcomm.robotcore.hardware.ColorSensor;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.DcMotorSimple;
        import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "DriveV3", group = "linear Opmode")                     // Register your Opmode. On the RC the Opmode will show up as the name string.

/* Extend the LinearOpMode.*/
public class Drive3 extends LinearOpMode {
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
        F1.setDirection(DcMotor.Direction.FORWARD);
        F2.setDirection(DcMotor.Direction.FORWARD);
        R1.setDirection(DcMotor.Direction.FORWARD);
        R2.setDirection(DcMotor.Direction.FORWARD);


        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);


        waitForStart();                                              // Wait for the game to start (driver presses PLAY).
        bias = 1.0;
        while (opModeIsActive())                                    /* Run until the end of the match (driver presses STOP). */ {
            heading = -1 * (Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x));  // Calculates the angle in radians measured counterclockwise from the positive x-axis and the heading (x, y)
            heading_degrees = heading * (180 / Math.PI);                        // Calculates the heading in degrees ??? not used again.
            leftstick_xsq = gamepad1.left_stick_x * gamepad1.left_stick_x;      // ??? this is not used again
            leftstick_ysq = gamepad1.left_stick_y * gamepad1.left_stick_y;              // ??? this is not used again


            if ((gamepad1.x) && (bias < 1.0))   //THis will let x & y move the power between 100% and 25%
                bias *= 1.0001;
            else if ((gamepad1.y) && (bias > 0.25))
                bias *= 0.9999;

            power = Math.sqrt(leftstick_xsq + leftstick_ysq);

            if (Math.abs(gamepad1.left_stick_x) > .1 || Math.abs(gamepad1.left_stick_y) > .1) {
/* This is the present code
                powerR1 = power * bias * (Math.cos(heading) + (Math.sin(heading)));
                powerF1 = power * bias * (Math.cos(heading) - (Math.sin(heading)));
                powerR2 = power * bias * (Math.cos(heading) + (Math.sin(heading)));
                powerF2 = power * bias * (Math.cos(heading) - (Math.sin(heading)));
*/
                powerF1 = -Math.sin(heading); // This moves it front to back
                powerF2 = -Math.sin(heading);
                powerR1 = -Math.sin(heading);
                powerR2 = -Math.sin(heading);


                powerF1 -= Math.cos(heading);// This adds in the side to side
                powerF2 += Math.cos(heading);
                powerR1 += Math.cos(heading);
                powerR2 -= Math.cos(heading);

                powerF1 *= power;            // This multiplies by the power
                powerF2 *= power;
                powerR1 *= power;
                powerR2 *= power;

                powerF1 *= bias;             // This multiplies by bias
                powerF2 *= bias;
                powerR1 *= bias;
                powerR2 *= bias;

                /*
                powerF1 = power * bias * (Math.cos(heading) - (Math.sin(heading)));
                powerF2 = power * bias * (Math.cos(heading) + (Math.sin(heading)));
                powerR1 = power * bias * (Math.cos(heading) - (Math.sin(heading)));
                powerR2 = power * bias * (Math.cos(heading) + (Math.sin(heading)));
                 */
            } else if (Math.abs(gamepad1.right_trigger) > .1) {
                powerF1 = -bias * gamepad1.right_trigger; // robot spin
                powerF2 = bias * gamepad1.right_trigger;
                powerR1 = -bias * gamepad1.right_trigger;
                powerR2 = bias * gamepad1.right_trigger;
            } else if (Math.abs(gamepad1.left_trigger) > .1) {
                powerF1 = bias * gamepad1.left_trigger; // robot spin
                powerF2 = -bias * gamepad1.left_trigger;
                powerR1 = bias * gamepad1.left_trigger;
                powerR2 = -bias * gamepad1.left_trigger;
            } else {
                powerF1 = 0;
                powerF2 = 0;
                powerR1 = 0;
                powerR2 = 0;
            }

                if (gamepad1.dpad_up) {
                    A1.setPower(-1);
                } else if (gamepad1.dpad_down) {
                    A1.setPower(1);
                } else {
                    A1.setPower(0);
                }
            if (gamepad1.x) {    //This Secetion of code is used to turn on each wheel by the name.
                powerF1 = 1.0; //green
                powerF2 = 0.0;
                powerR1 = 0.0;
                powerR2 = 0.0;
            } else if (gamepad1.y) {
                powerF1 = 0.0;
                powerF2 = 1.0; //red
                powerR1 = 0.0;
                powerR2 = 0.0;
            } else if (gamepad1.a) {
                powerF1 = 0.0;
                powerF2 = 0.0;
                powerR1 = 1.0; //blue
                powerR2 = 0.0;
            } else if (gamepad1.b) {
                powerF1 = 0.0;
                powerF2 = 0.0;
                powerR1 = 0.0;
                powerR2 = 1.0; //yellow
            }
//this is a test

            telemetry.addData("powerF1: ", powerF1);
            telemetry.addData("power: ", power);
            telemetry.addData("bias: ", bias);
            telemetry.addData("heading: ", heading);
            telemetry.addData("cos(): ", Math.cos(heading));
            telemetry.addData("sin(): ", Math.sin(heading));
            F1.setPower(powerF1);
            F2.setPower(powerF2);
            R1.setPower(powerR1);
            R2.setPower(powerR2);
            telemetry.update();
            }
        }
    }



