package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Doamne ajuta sa mearga", group="OpMode")
public class CoduOficial extends RobotHardware {
    private ElapsedTime runtime = new ElapsedTime();

   // double leftPower;
   // double rightPower;

    @Override
    public void runOpMode() throws InterruptedException {
        init_hardware(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {
          //  double drive = -gamepad1.left_stick_y;
          //  double turn = gamepad1.right_stick_x;
          //  leftPower = Range.clip(drive + turn, -1.0, 1.0);
          //  rightPower = Range.clip(drive - turn, -1.0, 1.0);
          //  motorFs.setPower(leftPower);
          //  motorSd.setPower(rightPower);
          //  motorSs.setPower(leftPower);
          //  motorFd.setPower(rightPower);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
           // telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
                if (gamepad2.back && gamepad2.start)
                {
                    odoWheelY.setPosition(0);
                    odoWheelX.setPosition(0);
                }
            //motoareClasic();
                intake();
                wheelMovement();
                lanttake();
                Launch();
        }
    }
}


