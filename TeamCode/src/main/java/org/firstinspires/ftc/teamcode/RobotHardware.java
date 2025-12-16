package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//Pentru init, casi e gay
public class RobotHardware extends LinearOpMode {
    public DcMotor motorFs, motorFd, motorSs, motorSd;
    public DcMotor[] wheelMotors = new DcMotor[4];
    public DcMotor intakeMotor;
    public DcMotor shooterStanga, shooterDreapta;
    public Servo intakeServo;
    public DcMotor cureaMT;
    public Servo bila;//casi e gay
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
        cureaMT = ahwMap.get(DcMotor.class,"curea mt");
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

        frana();

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

    @Override//trb ca sa nu dea eroare
    public void runOpMode() throws InterruptedException {}
}
