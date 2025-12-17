package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class RobotHardware extends LinearOpMode {
    public DcMotor motorFs, motorFd, motorSs, motorSd;
    public DcMotor[] wheelMotors = new DcMotor[4];
    public DcMotor intakeMotor;
    public DcMotor shooterStanga, shooterDreapta;
    public Servo intakeServo;
    public DcMotor lantMT;
    public Servo bila;
    double shooterPower = 0;//casi e gay
    boolean toggleB = false;
    boolean toggleY = false;
    boolean toggleX = false;
    boolean prev_pressedX = false;
    boolean prev_pressedY = false;
    boolean prev_pressedB = false;
    Servo odoWheelY, odoWheelX;

    void init_hardware(HardwareMap ahwMap) {
        motorFs = ahwMap.get(DcMotor.class, "motor Fs");
        motorSs = ahwMap.get(DcMotor.class, "motor Ss");
        motorSd = ahwMap.get(DcMotor.class, "motor Sd");
        motorFd = ahwMap.get(DcMotor.class, "motor Fd");
        intakeMotor = ahwMap.get(DcMotor.class, "motor intake");
        shooterStanga = ahwMap.get(DcMotor.class, "shooter stanga");
        shooterDreapta = ahwMap.get(DcMotor.class, "shooter dreapta");
        intakeServo = ahwMap.get(Servo.class, "servo intake");
        lantMT = ahwMap.get(DcMotor.class,"lant mt");
        bila = ahwMap.get(Servo.class,"bila");

        odoWheelX = ahwMap.get(Servo.class, "odometry X");
        odoWheelY = ahwMap.get(Servo.class, "odometry Y");

        setOdoPositions(Utils.ODOMETRY_UP);

        odoWheelX.setDirection(Servo.Direction.FORWARD);
        odoWheelY.setDirection(Servo.Direction.REVERSE);


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

        for (DcMotor motor : wheelMotors)
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        for (DcMotor motor : wheelMotors)
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void frana() {
        for (DcMotor motor : wheelMotors)
            motor.setPower(0);
    }
    public void setOdoPositions(float position) {
        odoWheelX.setPosition(position);
        odoWheelY.setPosition(position + 0.01f);
    }

    //----------------INTAKEEEEE------------

    public void intake() {


        if (!prev_pressedX && gamepad2.x) {
            toggleX = !toggleX;
        }

        if (toggleX) {

            intakeMotor.setPower(-1);
            intakeServo.setPosition(0);

        } else {

            intakeMotor.setPower(0);
            intakeServo.setPosition(0.5);
        }

        prev_pressedX = gamepad2.x;

        if (gamepad2.a){
            bila.setPosition(0);
        }
        else {
            bila.setPosition(0.5);
        }
    }

    //---------------------Launch-------------------------------
    public void Launch()
    {
        if (gamepad2.dpad_up) //creste puterea
            shooterPower = 1;

        if (gamepad2.dpad_down) //scade puterea
            shooterPower = -1;

        if (gamepad2.dpad_left)
            shooterPower = 0.5;

        if (gamepad2.dpad_right)
            shooterPower = 0.25;

        if (!prev_pressedY && gamepad2.y) {
            toggleY = !toggleY;
        }

        if (toggleY) {
            shooterStanga.setPower(-1);
            shooterDreapta.setPower(1);
        } else {
            shooterStanga.setPower(0);
            shooterDreapta.setPower(0);
        }

        prev_pressedY = gamepad2.y;
    }

    //------------LANT-------------
    public void lanttake(){

        if (!prev_pressedB && gamepad2.b) {
            toggleB = !toggleB;
        }

        if (toggleB) {
            lantMT.setPower(-1);
        } else {
            lantMT.setPower(0);
        }

        prev_pressedB = gamepad2.b;
    }

    //----------------WHEELS----------------
    public void wheelMovement() {
        if (gamepad1.a) {
            frana();
            return;
        }

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

    @Override//trb ca sa nu dea eroare
    public void runOpMode() throws InterruptedException {}
}
