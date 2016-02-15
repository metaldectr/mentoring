package com.romario.mentoring.runnable.lifelock;

import com.romario.mentoring.model.lifelock.Instruction;
import com.romario.mentoring.model.lifelock.MyReader;
import org.apache.log4j.Logger;

public class LifeLockThread extends Thread {
    private static final Logger sLogger = Logger.getLogger(LifeLockThread.class);
    private MyReader reader1;
    private MyReader reader2;
    private Instruction instruction;

    public LifeLockThread(String name) {
        super(name);
    }

    public LifeLockThread withReaders(final MyReader reader1, final MyReader reader2) {
        this.reader1 = reader1;
        this.reader2 = reader2;
        return this;
    }

    public LifeLockThread withInstruction(final Instruction instruction) {
        this.instruction = instruction;
        return this;
    }

    @Override
    public void run() {
        if (reader1 == null || reader2 == null || instruction == null) {
            throw new IllegalStateException("Thread not initialized");
        }

        reader1.doPrint(instruction, reader2);
    }
}
