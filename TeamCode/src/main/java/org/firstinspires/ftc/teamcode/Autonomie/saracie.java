package org.firstinspires.ftc.teamcode.Autonomie;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotHardware;
@Autonomous (name = "auto de saraci", group = "AUTO")
public class saracie extends RobotHardware {
    public void runOpMode(){
        init_hardware(hardwareMap);
        initAuto();
        waitForStart();

        while(opModeIsActive() && !isStopRequested()){
            motorFd.setPower(0.1);
            motorSs.setPower(0.1);
            motorSd.setPower(0.1);
            motorFs.setPower(0.1);
            sleep(99999);
        }

    }
}

