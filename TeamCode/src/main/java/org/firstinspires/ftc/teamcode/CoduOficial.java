package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Doamne ajuta sa mearga", group="Iterative OpMode")
public class CoduOficial extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor motorFs, motorFd, motorSs, motorSd;
    public DcMotor[] wheelMotors = new DcMotor[4];
   // private DcMotor brat = null;
    private DcMotor intakeMotor;
    //private DcMotor motorFs1;
    private DcMotor shooterStanga;
    private DcMotor shooterDreapta;
    private Servo intakeServo;

    double leftPower;
    double rightPower;
    double drive, turn;

    @Override
    public void runOpMode() throws InterruptedException {
        motorFs = hardwareMap.get(DcMotor.class, "motor Fs");
        motorSs = hardwareMap.get(DcMotor.class, "motor Ss");
        motorSd = hardwareMap.get(DcMotor.class, "motor Sd");
        motorFd = hardwareMap.get(DcMotor.class, "motor Fd");
        //brat = hardwareMap.get(DcMotor.class, "motor brat");
        intakeMotor = hardwareMap.get(DcMotor.class, "motor intake");
        shooterStanga = hardwareMap.get(DcMotor.class, "shooter stanga");
        shooterDreapta = hardwareMap.get(DcMotor.class, "shooter dreapta");
        intakeServo = hardwareMap.get(Servo.class, "servo intake");


        motorFs.setDirection(DcMotor.Direction.REVERSE);
        motorFd.setDirection(DcMotor.Direction.REVERSE);
        motorSs.setDirection(DcMotor.Direction.FORWARD);
        motorSd.setDirection(DcMotor.Direction.REVERSE);
        /// --------------launch----------------------------
        shooterStanga.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterDreapta.setDirection(DcMotorSimple.Direction.REVERSE);
        shooterStanga.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooterDreapta.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        wheelMotors = new DcMotor[]{motorFs, motorFd, motorSs, motorSd};

        frana();

        for (DcMotor motor : wheelMotors)
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        for (DcMotor motor : wheelMotors)
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

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

    public void frana() {
        for (DcMotor motor : wheelMotors)
            motor.setPower(0);
    }
    public void setIntakeServoPosition(float pos) {
        intakeServo.setPosition(pos);
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

        if (gamepad2.x) {
            intakeMotor.setPower(-1.0);
            setIntakeServoPosition(intakeservo_run);
        }
        if (gamepad2.dpad_up) {
            intakeMotor.setPower(0);
            setIntakeServoPosition(intakeservo_stop);
        }
    }
    //---------------------Launch-------------------------------
    private void Launch()
    {
        boolean shooterToggle = false;
        boolean prevButon = false;
        double shooterPower = 1;
        boolean y_press = gamepad2.y;
        if (y_press && !prevButon)
            shooterToggle = !shooterToggle;
        prevButon = y_press;

        if (shooterToggle) {
            shooterStanga.setPower(shooterPower);
            shooterDreapta.setPower(shooterPower);
        } else {
            shooterStanga.setPower(0);
            shooterDreapta.setPower(0);

        }
        if (gamepad2.dpad_up) //creste puterea
            shooterPower += 0.05;
        if (gamepad2.dpad_down) //scade puterea
            shooterPower -= 0.05;

        shooterPower = Math.max(0,Math.min(0.35  , shooterPower));//creste puterea de la 0 la puterea pusa

        if (gamepad2.dpad_left)
            shooterPower = 0.5;

    }
    public static float intakeservo_stop = 0;
    public static float intakeservo_run = -1;
}


