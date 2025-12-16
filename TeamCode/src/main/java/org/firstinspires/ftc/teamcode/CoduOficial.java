package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Doamne ajuta sa mearga", group="Iterative OpMode")
public class CoduOficial extends RobotHardware {
    private ElapsedTime runtime = new ElapsedTime();

    double leftPower;
    double rightPower;
    boolean shooterToggle = false;
    boolean prevButon = false;
    double shooterPower = 0;
    boolean y_press = false;

    @Override
    public void runOpMode() throws InterruptedException {
        init_hardware(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);

            motorFs.setPower(leftPower);
            motorSd.setPower(rightPower);
            motorSs.setPower(leftPower);
            motorFd.setPower(rightPower);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);

            intake();
            wheelMovement();
            frana();
            cureatake();
            Launch();
        }
    }

    private void wheelMovement() {
        float x, y, t;
        t = -gamepad1.right_stick_x;
        y = gamepad1.right_trigger - gamepad1.left_trigger;
        x = gamepad1.left_stick_x;

        float fd, fs, sd, ss;
        fs = y + x - t;
        fd = y - x + t;
        ss = y - x - t;
        sd = y + x + t;

        motorFs.setPower(fs);
        motorFd.setPower(fd);
        motorSs.setPower(ss);
        motorSd.setPower(sd);
    }

   // public void setIntakeServoPosition(float pos) {
   //     intakeServo.setPosition(pos);
   // }
    public void cureatake(){
        cureaMT.setPower(-1);
    }

    //----------------INTAKEEEEE------------
    private void intake() {
        //  boolean intakeToggle = false;
        //  boolean intakeButonPrev = false;

        //____________in while__________________________________
        //  boolean intake_buton = gamepad2.a;
        //  boolean outtake_buton = gamepad2.b;

        //  if (intake_buton && !intakeButonPrev) //toggle
        //      intakeToggle = !intakeToggle; //trebuie schimbat nu sunt sigur (l-am rezolvat) (nu l-ai rezolvat...)z(sa imi iei bilele)
        //  intakeButonPrev = intake_buton;

        //  if (outtake_buton)
        //      intakeMotor.setPower(-1.0);
        //  else {
        //      if (intakeToggle)
        //          intakeMotor.setPower(1.0);
        //      else
        //         intakeMotor.setPower(0);
   // }
        if (gamepad2.xWasPressed()) {
            intakeMotor.setPower(-1);
            intakeServo.setPosition(0);
        }

        if(gamepad2.xWasReleased()) {
            intakeMotor.setPower(0);
            intakeServo.setPosition(0.5);//0 - reverse, 0.5 - stop, 1 - fata
        }

         if (gamepad2.a){
            bila.setPosition(0);
         }
         else {
             bila.setPosition(0.5);
         }
    }
    //---------------------Launch-------------------------------
    private void Launch()
    {
        if (gamepad2.dpad_up) //creste puterea
            shooterPower = 1;
        if (gamepad2.dpad_down) //scade puterea
            shooterPower = -1;
        if (gamepad2.dpad_left)
            shooterPower = 0.5;
        if (gamepad2.dpad_right)
            shooterPower = 0.25;
//        shooterPower = Math.max(0,Math.min(0.35  , shooterPower));//creste puterea de la 0 la puterea pusa
        if (gamepad2.y) {
            shooterStanga.setPower(-1);
            shooterDreapta.setPower(1);
        } else {
            shooterStanga.setPower(0);
            shooterDreapta.setPower(0);
        }
    }
  // public static float intakeservo_stop = 0f;
  // public static int intakeservo_run = -1;
}


