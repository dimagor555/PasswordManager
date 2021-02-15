package ru.dimagor555.usecase;

public interface HasMasterPassword {
    void execute(Callback callback);

    interface Callback {
        void onHasMasterPassword();

        void onNotHasMasterPassword();
    }
}
