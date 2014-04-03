package experiment.fsl.templet;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class MapNodeTemplet {
    final int path;
    final int index;
    final int type;
    final String nextPath;

    public MapNodeTemplet(JSONObject jsonObject) {
        this.path = jsonObject.getInteger("p");
        this.index = jsonObject.getInteger("i");
        this.type = jsonObject.getInteger("o");
        this.nextPath = jsonObject.getString("n");
    }


}