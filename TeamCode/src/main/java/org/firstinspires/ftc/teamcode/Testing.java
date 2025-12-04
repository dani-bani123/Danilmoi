package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Sus jos")
public class Testing extends LinearOpMode
{
    DcMotor showtime, bigbang, triplelux, bigd;

    @Override
    public void runOpMode() throws InterruptedException {
        //INIT - ruleaza o data

        showtime = hardwareMap.get(DcMotor.class, "motor Fd");
        bigbang = hardwareMap.get(DcMotor.class, "motor Fs");
        triplelux = hardwareMap.get(DcMotor.class, "motor Sd");
        bigd = hardwareMap.get(DcMotor.class, "motor Ss");
        bigd.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        //PLAY - se repeta pana cand se opreste
        while (opModeIsActive()) {
            showtime.setPower(gamepad1.left_stick_y);
            bigbang.setPower(gamepad1.left_stick_y);
            triplelux.setPower(gamepad1.left_stick_y);
            bigd.setPower(gamepad1.left_stick_y);
        }
    }
}