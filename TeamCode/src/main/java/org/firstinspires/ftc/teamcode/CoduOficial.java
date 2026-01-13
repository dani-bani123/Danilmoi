package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="Doamne ajuta sa mearga", group="OpMode")
public class CoduOficial extends RobotHardware {
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        init_hardware(hardwareMap);

        waitForStart();

        while (opModeIsActive()) {

            telemetry.addData("Status", "Run Time: " + runtime.toString());
                intake();
                wheelMovement();
                lanttake();
                Launch();
        }
    }
}


