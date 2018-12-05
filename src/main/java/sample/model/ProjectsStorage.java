package sample.model;

import sample.model.interfaces.Scanword;

import java.util.*;

/**
 * Created by VAUst on 28.10.2018.
 */
public class ProjectsStorage {
    private Map<String, Block> blockStorage;

    public ProjectsStorage() {
        blockStorage = new HashMap<String, Block>();
    }

    public Block getBlock(String blockName) {
        return blockStorage.get(blockName);
    }

    public void addProject(Block block) {
        if (blockStorage.size() == 0) {
            blockStorage.put(block.getName(), block);
            return;
        }
        if (blockStorage.size() > 0 && !blockStorage.containsValue(block)) {
            blockStorage.put(block.getName(), block);
        }
    }

    public void removeProject(String blockName) {
        if (blockStorage.containsKey(blockName)) {
            blockStorage.remove(blockName);
        }
    }

    public Map<String, List<String>> getBlocksAndScanwordsName() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(String key: blockStorage.keySet()) {
            List<String> list = new LinkedList<String>();
            for(Scanword scanword: blockStorage.get(key).getScanwords()) {
                list.add(scanword.getName());
            }
            map.put(key, list);
        }
        return map;
    }
}
