
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Disabled
@TeleOp(name="Doamne ajuta sa mearga", group="Iterative OpMode")
public class BasicOpMode_Iterative extends OpMode
{

    private ElapsedTime runtime = new ElapsedTime();
    public DcMotor motorFs, motorFd, motorSs, motorSd;
    public DcMotor[] wheelMotors = new DcMotor[4];
    private DcMotor brat = null;
    private DcMotor intakeMotor;




    @Override
     public void init() {
        telemetry.addData("Status", "Initialized");

        motorFs = hardwareMap.get(DcMotor.class, "motor Fs");
        motorSs = hardwareMap.get(DcMotor.class, "motor Ss");
        motorSd = hardwareMap.get(DcMotor.class,"motor Sd");
        motorFd =hardwareMap.get(DcMotor.class,"motor Fd");
        brat = hardwareMap.get(DcMotor.class, "motor brat");
        intakeMotor = hardwareMap.get(DcMotor.class , "motor In");

        motorFs.setDirection(DcMotor.Direction.FORWARD); motorFd.setDirection(DcMotor.Direction.FORWARD);
        motorSs.setDirection(DcMotor.Direction.REVERSE); motorSd.setDirection(DcMotor.Direction.FORWARD);

        wheelMotors = new DcMotor[] {motorFs, motorFd, motorSs, motorSd};

        frana();

        for(DcMotor motor : wheelMotors)
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        for(DcMotor motor : wheelMotors)
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        double leftPower;
        double rightPower;

        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;

        motorFs.setPower(leftPower);
        motorSd.setPower(rightPower);
        motorSs.setPower(leftPower);
        motorFd.setPower(rightPower);
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
    }
    float x, y;
    private void wheelMovement() {
        float t = -gamepad1.right_stick_x;
        normalmov();
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
        private void normalmov(){
            y = gamepad1.right_trigger - gamepad1.left_trigger;
            x = gamepad1.left_stick_x;

        }
    public void frana() {
        for (DcMotor motor : wheelMotors)
            motor.setPower(0);
    }
    //___________________INTAKE______________________________________
    private void intake (){
        boolean intakeToggle=false;
        boolean intakeButonPrev=false;
        double intakePower = 1.0;
        double outtakePower = 1.0;
    //____________in while__________________________________
        boolean intake_buton = gamepad2.a;
        boolean outtake_buton = gamepad2.b;

        if(intake_buton && !outtake_buton) //toggle
            intakeToggle = !intakeToggle; //trebuie schimbat nu sunt sigur (l-am rezolvat)

        if (outtake_buton) {
            intakeMotor.setPower(-1.0);
        }
        else
        {
            if(intakeToggle){
                intakeMotor.setPower(1.0);
            }
            else{
               intakeMotor.setPower(0);
            }
        }


    }

    @Override
    public void stop() {
    }

}
