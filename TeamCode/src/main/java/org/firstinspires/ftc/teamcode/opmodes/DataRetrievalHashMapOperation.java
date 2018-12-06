package org.firstinspires.ftc.teamcode.opmodes;

@FunctionalInterface
public interface DataRetrievalHashMapOperation<Key, String> {
    String apply(Key key);
}
