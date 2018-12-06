package org.firstinspires.ftc.teamcode.opmodes;

@FunctionalInterface
public interface DataRemovalHashMapOperation<Key, Boolean> {
    Boolean apply(Key key);
}
