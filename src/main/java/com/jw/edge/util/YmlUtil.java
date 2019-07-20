package com.jw.edge.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.yaml.snakeyaml.Yaml;

public class YmlUtil {

    private String name;
    private String manufacture;
    private String model;
    private String description;
    List<DeviceResource> deviceResources = new ArrayList<DeviceResource>();
    List<Resource> resources = new ArrayList<Resource>();
    List<Command> commands = new ArrayList<Command>();

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getManufacture() {
        return manufacture;
    }
    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public List<DeviceResource> getDeviceResources() {
        return deviceResources;
    }
    public void setDeviceResources(List<DeviceResource> deviceResources) {
        this.deviceResources = deviceResources;
    }
    public List<Resource> getResources() {
        return resources;
    }
    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
    public List<Command> getCommands() {
        return commands;
    }
    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
    public static void main(String[] args) throws Exception{

        /*设备对象包括设备名(name)，制造商(manufacture)，设备型号(model)，设备描述(description），设备资源列表（deviceResources）,设备操作列表(resources),设备命令列表（commands）*/
        YmlUtil modelprofile = new YmlUtil();
        modelprofile.setName("MBUS_RTH_LCD");
        modelprofile.setManufacture("DATANAB");
        modelprofile.setModel("MBUS_RTH_LCD");
        modelprofile.setDescription("Modbus Enabled Room Temperature/Humididty Sensor with LCD");

        /*设备资源段，设置设备资源名称（name），资源描述（description），资源属性（property）（包括value属性和units属性）*/

        DeviceResource desrc = new DeviceResource();
        desrc.setName("TemperatureDegF");
        desrc.setDescription("Room Temperature in Degrees Fahrenheit.");
        /*资源1的属性设置*/
        Value value01 = new Value();
        value01.setType("float");
        value01.setReadWrite("R");
        value01.setSize(1);
        value01.setScale(0.1f);
        value01.setMinimum(40f);
        value01.setMaximum(122f);
        value01.setDefaultValue("1");
        List<Value> value11 = new ArrayList<Value>();
        value11.add(value01);

        Units units01 = new Units();
        units01.setType("String");
        units01.setReadWrite("R");
        units01.setDefaultValue("degrees fahrenheit");
        List<Units> units11 = new ArrayList<Units>();
        units11.add(units01);

        Property property01 = new Property();
        property01.setValue(value11);
        property01.setUnits(units11);

        List<Property> properties = new ArrayList<Property>();
        properties.add(property01);
        desrc.setProperties(properties);

        /*资源2的属性设置*/
        DeviceResource desrc1 = new DeviceResource();
        desrc1.setName("TemperatureDegC");
        desrc1.setDescription("Room Temperature in Degrees Celsius.");

        Value value02 = new Value();
        value02.setType("float");
        value02.setReadWrite("R");
        value02.setSize(1);
        value02.setScale(0.1f);
        value02.setMinimum(5.0f);
        value02.setMaximum(50f);
        value02.setDefaultValue("1");
        List<Value> value22 = new ArrayList<Value>();
        value22.add(value02);

        Units units02 = new Units();
        units02.setType("String");
        units02.setReadWrite("R");
        units02.setDefaultValue("degrees Celsius");
        List<Units> units22 = new ArrayList<Units>();
        units22.add(units02);

        Property property02 = new Property();
        property02.setValue(value22);
        property02.setUnits(units22);

        List<Property> properties1 = new ArrayList<Property>();
        properties1.add(property02);
        desrc1.setProperties(properties1);

        /*资源3的属性设置*/
        DeviceResource desrc2 = new DeviceResource();
        desrc2.setName("HumidityPercentRH");
        desrc2.setDescription("Room Humidity in %RH.");

        Value value03 = new Value();
        value03.setType("float");
        value03.setReadWrite("R");
        value03.setSize(1);
        value03.setScale(0.1f);
        value03.setMinimum(0.0f);
        value03.setMaximum(95.0f);
        value03.setDefaultValue("1");
        List<Value> value33 = new ArrayList<Value>();
        value33.add(value03);

        Units units03 = new Units();
        units03.setType("String");
        units03.setReadWrite("R");
        units03.setDefaultValue("%RH");
        List<Units> units33 = new ArrayList<Units>();
        units33.add(units03);

        Property property03 = new Property();
        property03.setValue(value33);
        property03.setUnits(units33);

        List<Property> properties2 = new ArrayList<Property>();
        properties2.add(property03);
        desrc2.setProperties(properties2);

        /*设备资源列表*/
        List<DeviceResource> deviceResources = new ArrayList<DeviceResource>();
        deviceResources.add(desrc);
        deviceResources.add(desrc1);
        deviceResources.add(desrc2);

        /*设备资源列表设置*/
        modelprofile.setDeviceResources(deviceResources);

        /*设备操作包括名称（name），和get方法*/

        /*资源1的操作设置*/
        Resource src1 = new Resource();
        src1.setName("TemperatureDegF");
        Resourceget resourceget01 = new Resourceget();
        resourceget01.setIndex("1");
        resourceget01.setOperation("get");
        resourceget01.setObject("TemperatureDegF");
        resourceget01.setParameter("TemperatureDegF");
        resourceget01.setProperty("value");
        List<Resourceget> resourceget11 = new ArrayList<Resourceget>();
        resourceget11.add(resourceget01);
        src1.setGet(resourceget11);


        /*资源2的操作设置*/
        Resource src2 = new Resource();
        src2.setName("TemperatureDegC");
        Resourceget resourceget02 = new Resourceget();
        resourceget02.setIndex("1");
        resourceget02.setOperation("get");
        resourceget02.setObject("TemperatureDegC");
        resourceget02.setParameter("TemperatureDegC");
        resourceget02.setProperty("value");
        List<Resourceget> resourceget22 = new ArrayList<Resourceget>();
        resourceget22.add(resourceget02);
        src2.setGet(resourceget22);


        /*资源3的操作设置*/
        Resource src3 = new Resource();
        src3.setName("HumidityPercentRH");
        Resourceget resourceget03 = new Resourceget();
        resourceget03.setIndex("1");
        resourceget03.setOperation("get");
        resourceget03.setObject("HumidityPercentRH");
        resourceget03.setParameter("HumidityPercentRH");
        resourceget03.setProperty("value");
        List<Resourceget> resourceget33 = new ArrayList<Resourceget>();
        resourceget33.add(resourceget03);
        src3.setGet(resourceget33);


        /*操作列表*/
        List<Resource> resources = new ArrayList<Resource>();
        resources.add(src1);
        resources.add(src2);
        resources.add(src3);

        /*设备操作属性设置*/
        modelprofile.setResources(resources);

        /*设备命令包括命令名（name），get方法（包括路径（path）和响应（responses））*/

        /*设备资源1命令设置*/
        Command cmd = new Command();
        Get get01 = new Get();
        Response respon00 = new Response();
        respon00.setCode("200");
        respon00.setDescription("Get the temperature in degrees F");
        respon00.setExpectedValues("[\"TemperatureDegF\"]");
        Response respon01 = new Response();
        respon01.setCode("503");
        respon01.setDescription("service unavailable");
        respon01.setExpectedValues("[]");
        List<Response> responses = new ArrayList<Response>();
        responses.add(respon00);
        responses.add(respon01);
        get01.setPath("/api/v1/device/{devicwld}/TemperatureDegF");
        get01.setResponses(responses);
        List<Get> get = new ArrayList<Get>();
        get.add(get01);
        cmd.setName("TemperatureDegF");
        cmd.setGet(get);

        /*设备资源2命令设置*/
        Command cmd1 = new Command();
        Get get02 = new Get();
        Response respon10 = new Response();
        respon10.setCode("200");
        respon10.setDescription("Get the temperature in degrees C");
        respon10.setExpectedValues("[\"TemperatureDegC\"]");
        Response respon11 = new Response();
        respon11.setCode("503");
        respon11.setDescription("service unavailable");
        respon11.setExpectedValues("[]");
        List<Response> responses1 = new ArrayList<Response>();
        responses1.add(respon10);
        responses1.add(respon11);
        get02.setPath("/api/v1/device/{devicwld}/TemperatureDegC");
        get02.setResponses(responses1);

        List<Get> get1 = new ArrayList<Get>();
        get1.add(get02);
        cmd1.setName("TemperatureDegC");
        cmd1.setGet(get1);

        /*设备资源3命令设置*/
        Command cmd2 = new Command();
        Get get03 = new Get();
        Response respon20 = new Response();
        respon20.setCode("200");
        respon20.setDescription("Get the humidity in %RH");
        respon20.setExpectedValues("[\"HumidityPercentRH\"]");
        Response respon21 = new Response();
        respon21.setCode("503");
        respon21.setDescription("service unavailable");
        respon21.setExpectedValues("[]");
        List<Response> responses2 = new ArrayList<Response>();
        responses2.add(respon20);
        responses2.add(respon21);
        get03.setPath("/api/v1/device/{devicwld}/HumidityPercentRH");
        get03.setResponses(responses2);
        List<Get> get2 = new ArrayList<Get>();
        get2.add(get03);
        cmd2.setName("HumidityPercentRH");
        cmd2.setGet(get2);

        /*设备命令列表*/
        List<Command> commands = new ArrayList<Command>();
        commands.add(cmd);
        commands.add(cmd1);
        commands.add(cmd2);

        /*设备命令属性设置*/
        modelprofile.setCommands(commands);
        /*生成yaml配置文件*/
        Yaml yaml = new Yaml();
        try {
            yaml.dump(modelprofile,new FileWriter("src/main/resources/deviceProfile/remodelprofile.yaml"));/*设置生成文件的路径及名称*/
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
    class DeviceResource {
        private String name;
        private String description;
        List<Property> properties = new ArrayList<Property>();


        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public void setProperties(List<Property> properties) {
            this.properties = properties;
        }
        public List<Property> getProperties() {
            return properties;
        }




    }
    class Resource {
        private String name;
        List<Resourceget> get = new ArrayList<Resourceget>();

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public void setGet(List<Resourceget> get) {
            this.get= get;
        }
        public List<Resourceget> getGet() {
            return get;
        }
    }
    class Command {
        private String name;
        List<Get> get = new ArrayList<Get>();

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
        public void setGet(List<Get> get) {
            this.get = get;
        }
        public List<Get> getGet() {
            return get;
        }
    }
    class Property {
        List<Value> value = new ArrayList<Value>();
        List<Units> units = new ArrayList<Units>();

        public void setValue(List<Value> value) {
            this.value = value;
        }
        public List<Value> getValue() {
            return value;
        }
        public void setUnits(List<Units> units) {
            this.units = units;
        }
        public List<Units> getUnits() {
            return units;
        }
    }
    class Value {
        private String type;
        private String readWrite;
        private float size;
        private float scale;
        private float minimum;
        private float maximum;
        private String defaultValue;
        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
        public void setReadWrite(String readWrite) {
            this.readWrite = readWrite;
        }
        public String getreadWrite() {
            return readWrite;
        }
        public void setSize(float size) {
            this.size = size;
        }
        public float getSize() {
            return size;
        }
        public void setScale(float scale) {
            this.scale = scale;
        }
        public float getScale() {
            return scale;
        }
        public void setMinimum(float minimum) {
            this.minimum = minimum;
        }
        public float getMinimum() {
            return minimum;
        }
        public void setMaximum(float maximum) {
            this.maximum = maximum;
        }
        public float getMaximum() {
            return maximum;
        }
        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }
        public String getDefaultValue() {
            return defaultValue;
        }
    }
    class Units {
        private String type;
        private String readWrite;
        private String defaultValue;

        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
        public void setReadWrite(String readWrite) {
            this.readWrite = readWrite;
        }
        public String getReadWrite() {
            return readWrite;
        }
        public void setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
        }
        public String getDefaultValue() {
            return defaultValue;
        }

    }
    class Get {
        private String path;
        List<Response> responses = new ArrayList<Response>();

        public void setPath(String path) {
            this.path = path;
        }
        public String getPath() {
            return path;
        }
        public void setResponses(List<Response> responses) {
            this.responses = responses;
        }
        public List<Response> getResponses() {
            return responses;
        }

    }
    class Response {
        private String code;
        private String description;
        private String expectedValues;

        public void setCode(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getDescription() {
            return description;
        }
        public void setExpectedValues(String expectedValues) {
            this.expectedValues = expectedValues;
        }
        public String getExpectedValues() {
            return expectedValues;
        }
    }
    class Resourceget {
        private String index;
        private String operation;
        private String object;
        private String parameter;
        private String property;

        public void setIndex(String index) {
            this.index = index;
        }
        public String getIndex() {
            return index;
        }
        public void setOperation(String operation) {
            this.operation = operation;
        }
        public String getOperation() {
            return operation;
        }
        public void setObject(String object) {
            this.object = object;
        }
        public String getObject() {
            return object;
        }
        public void setParameter(String parameter) {
            this.parameter = parameter;
        }
        public String getParameter() {
            return parameter;
        }
        public void setProperty(String property) {
            this.property = property;
        }
        public String getProperty() {
            return property;
        }
}
