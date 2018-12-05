package sample.model;

import sample.model.interfaces.Scanword;

import java.util.*;

/**
 * Created by VAUst on 28.10.2018.
 */
public class PatternsStorage {
    Map<String, Block> blockStorage;
    Map<String, Scanword> scanwordStorage;

    public PatternsStorage() {
        blockStorage = new HashMap<String, Block>();
        scanwordStorage = new HashMap<String, Scanword>();
    }

    public void addBlock(String blockPatternName, Block blockPattern) {
        blockStorage.put(blockPatternName, blockPattern);
    }

    public Block removeBlock(String blockPatternName) {
        return blockStorage.remove(blockPatternName);
    }

    public Block getBlock(String blockPatternName) {
        return blockStorage.get(blockPatternName);

    }

    public List<Block> getBlocks() {
        return new ArrayList<Block> (blockStorage.values());
    }

    public void addScanword(String stringPatternName, Scanword scanwordPattern) {
        scanwordStorage.put(stringPatternName, scanwordPattern);
    }

    public Scanword removeScanword(String scanwordPatternName) {
        return scanwordStorage.remove(scanwordPatternName);
    }

    public Scanword getScanword(String scanwordPatternName) {
        return scanwordStorage.get(scanwordPatternName);
    }

    public List<Scanword> getScanwords() {
        return new ArrayList<Scanword> (scanwordStorage.values());
    }
}
