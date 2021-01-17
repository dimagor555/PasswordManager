package ru.dimagor555.hasher;

import ru.dimagor555.domain.port.Hasher;

public class DefaultHasher implements Hasher {
    @Override
    public String hash(String data) {
        //dummy implementation for debug
        return data.hashCode() + "";
    }
}
