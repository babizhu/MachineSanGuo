package experiment.serialize;



/**
 * 模版
 * @author liukun
 * 2013-11-16 0:06:02
 */
public class HeroTemplet {

    /**
	 * id
	 */
    private final int id;



	/**
	 * id
	 */
	public int getId() {
		return id;
	}
/**
	 * 名称
	 */
    private final String name;



	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
/**
	 * 重量
	 */
    private final int weight;



	/**
	 * 重量
	 */
	public int getWeight() {
		return weight;
	}


	public HeroTemplet(HeroTemplet element, int id, String name, int weight) {
        this.id = id;


        this.name = name;
        this.weight = weight;
    }

	@Override
	public String toString() {
		return "HeroTemplet [id = " + id + ",name = " + name + ",weight = " + weight + "]";
	}

	/*自定义代码开始*//*自定义代码结束*/
}
