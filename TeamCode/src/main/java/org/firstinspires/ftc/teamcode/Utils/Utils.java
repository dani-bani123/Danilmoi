package org.firstinspires.ftc.teamcode.Utils;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;

//TODO change values for the auto
@Config
public class Utils {

    public static class AUTO{
        public static final Pose2d POSE_JOS = new Pose2d(0, 0, Math.PI);
        public static final Pose2d POSE_SUS = new Pose2d(0, 0, Math.PI);
    }
    //___________________ODO___________________
    public static final float ODOMETRY_UP = 0.48f;
    public static final float ODOMETRY_DOWN =0.64f;

}
