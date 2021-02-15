package ru.dimagor555.cryption;

import ru.dimagor555.usecase.GetCryptKey;

public class AesCryptorAutoSetKey extends AesCryptor {
    private final GetCryptKey getCryptKey;

    public AesCryptorAutoSetKey(GetCryptKey getCryptKey) {
        this.getCryptKey = getCryptKey;
    }

    @Override
    public String encrypt(String data) {
        setKeyIfNotHas();
        return super.encrypt(data);
    }

    @Override
    public String decrypt(String data) {
        setKeyIfNotHas();
        return super.decrypt(data);
    }

    private void setKeyIfNotHas() {
        if (!hasKey()) {
            String cryptKey = getCryptKey.execute();
            setKey(cryptKey);
        }
    }
}
