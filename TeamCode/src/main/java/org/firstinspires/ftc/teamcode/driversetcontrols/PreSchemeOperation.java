package org.firstinspires.ftc.teamcode.driversetcontrols;

@FunctionalInterface
public interface PreSchemeOperation<Post> {
    Post apply(Post pre);
}
