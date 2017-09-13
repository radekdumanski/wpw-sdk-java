/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.worldpay.innovation.wpwithin.rpc.types;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-09-04")
public class Device implements org.apache.thrift.TBase<Device, Device._Fields>, java.io.Serializable, Cloneable, Comparable<Device> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Device");

  private static final org.apache.thrift.protocol.TField UID_FIELD_DESC = new org.apache.thrift.protocol.TField("uid", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField SERVICES_FIELD_DESC = new org.apache.thrift.protocol.TField("services", org.apache.thrift.protocol.TType.MAP, (short)4);
  private static final org.apache.thrift.protocol.TField IPV4_ADDRESS_FIELD_DESC = new org.apache.thrift.protocol.TField("ipv4Address", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField CURRENCY_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("currencyCode", org.apache.thrift.protocol.TType.STRING, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new DeviceStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new DeviceTupleSchemeFactory();

  public java.lang.String uid; // required
  public java.lang.String name; // required
  public java.lang.String description; // required
  public java.util.Map<java.lang.Integer,Service> services; // required
  public java.lang.String ipv4Address; // required
  public java.lang.String currencyCode; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    UID((short)1, "uid"),
    NAME((short)2, "name"),
    DESCRIPTION((short)3, "description"),
    SERVICES((short)4, "services"),
    IPV4_ADDRESS((short)5, "ipv4Address"),
    CURRENCY_CODE((short)6, "currencyCode");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // UID
          return UID;
        case 2: // NAME
          return NAME;
        case 3: // DESCRIPTION
          return DESCRIPTION;
        case 4: // SERVICES
          return SERVICES;
        case 5: // IPV4_ADDRESS
          return IPV4_ADDRESS;
        case 6: // CURRENCY_CODE
          return CURRENCY_CODE;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.UID, new org.apache.thrift.meta_data.FieldMetaData("uid", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SERVICES, new org.apache.thrift.meta_data.FieldMetaData("services", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32), 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Service.class))));
    tmpMap.put(_Fields.IPV4_ADDRESS, new org.apache.thrift.meta_data.FieldMetaData("ipv4Address", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CURRENCY_CODE, new org.apache.thrift.meta_data.FieldMetaData("currencyCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Device.class, metaDataMap);
  }

  public Device() {
  }

  public Device(
    java.lang.String uid,
    java.lang.String name,
    java.lang.String description,
    java.util.Map<java.lang.Integer,Service> services,
    java.lang.String ipv4Address,
    java.lang.String currencyCode)
  {
    this();
    this.uid = uid;
    this.name = name;
    this.description = description;
    this.services = services;
    this.ipv4Address = ipv4Address;
    this.currencyCode = currencyCode;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Device(Device other) {
    if (other.isSetUid()) {
      this.uid = other.uid;
    }
    if (other.isSetName()) {
      this.name = other.name;
    }
    if (other.isSetDescription()) {
      this.description = other.description;
    }
    if (other.isSetServices()) {
      java.util.Map<java.lang.Integer,Service> __this__services = new java.util.HashMap<java.lang.Integer,Service>(other.services.size());
      for (java.util.Map.Entry<java.lang.Integer, Service> other_element : other.services.entrySet()) {

        java.lang.Integer other_element_key = other_element.getKey();
        Service other_element_value = other_element.getValue();

        java.lang.Integer __this__services_copy_key = other_element_key;

        Service __this__services_copy_value = new Service(other_element_value);

        __this__services.put(__this__services_copy_key, __this__services_copy_value);
      }
      this.services = __this__services;
    }
    if (other.isSetIpv4Address()) {
      this.ipv4Address = other.ipv4Address;
    }
    if (other.isSetCurrencyCode()) {
      this.currencyCode = other.currencyCode;
    }
  }

  public Device deepCopy() {
    return new Device(this);
  }

  @Override
  public void clear() {
    this.uid = null;
    this.name = null;
    this.description = null;
    this.services = null;
    this.ipv4Address = null;
    this.currencyCode = null;
  }

  public java.lang.String getUid() {
    return this.uid;
  }

  public Device setUid(java.lang.String uid) {
    this.uid = uid;
    return this;
  }

  public void unsetUid() {
    this.uid = null;
  }

  /** Returns true if field uid is set (has been assigned a value) and false otherwise */
  public boolean isSetUid() {
    return this.uid != null;
  }

  public void setUidIsSet(boolean value) {
    if (!value) {
      this.uid = null;
    }
  }

  public java.lang.String getName() {
    return this.name;
  }

  public Device setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public java.lang.String getDescription() {
    return this.description;
  }

  public Device setDescription(java.lang.String description) {
    this.description = description;
    return this;
  }

  public void unsetDescription() {
    this.description = null;
  }

  /** Returns true if field description is set (has been assigned a value) and false otherwise */
  public boolean isSetDescription() {
    return this.description != null;
  }

  public void setDescriptionIsSet(boolean value) {
    if (!value) {
      this.description = null;
    }
  }

  public int getServicesSize() {
    return (this.services == null) ? 0 : this.services.size();
  }

  public void putToServices(int key, Service val) {
    if (this.services == null) {
      this.services = new java.util.HashMap<java.lang.Integer,Service>();
    }
    this.services.put(key, val);
  }

  public java.util.Map<java.lang.Integer,Service> getServices() {
    return this.services;
  }

  public Device setServices(java.util.Map<java.lang.Integer,Service> services) {
    this.services = services;
    return this;
  }

  public void unsetServices() {
    this.services = null;
  }

  /** Returns true if field services is set (has been assigned a value) and false otherwise */
  public boolean isSetServices() {
    return this.services != null;
  }

  public void setServicesIsSet(boolean value) {
    if (!value) {
      this.services = null;
    }
  }

  public java.lang.String getIpv4Address() {
    return this.ipv4Address;
  }

  public Device setIpv4Address(java.lang.String ipv4Address) {
    this.ipv4Address = ipv4Address;
    return this;
  }

  public void unsetIpv4Address() {
    this.ipv4Address = null;
  }

  /** Returns true if field ipv4Address is set (has been assigned a value) and false otherwise */
  public boolean isSetIpv4Address() {
    return this.ipv4Address != null;
  }

  public void setIpv4AddressIsSet(boolean value) {
    if (!value) {
      this.ipv4Address = null;
    }
  }

  public java.lang.String getCurrencyCode() {
    return this.currencyCode;
  }

  public Device setCurrencyCode(java.lang.String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public void unsetCurrencyCode() {
    this.currencyCode = null;
  }

  /** Returns true if field currencyCode is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrencyCode() {
    return this.currencyCode != null;
  }

  public void setCurrencyCodeIsSet(boolean value) {
    if (!value) {
      this.currencyCode = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case UID:
      if (value == null) {
        unsetUid();
      } else {
        setUid((java.lang.String)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((java.lang.String)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((java.lang.String)value);
      }
      break;

    case SERVICES:
      if (value == null) {
        unsetServices();
      } else {
        setServices((java.util.Map<java.lang.Integer,Service>)value);
      }
      break;

    case IPV4_ADDRESS:
      if (value == null) {
        unsetIpv4Address();
      } else {
        setIpv4Address((java.lang.String)value);
      }
      break;

    case CURRENCY_CODE:
      if (value == null) {
        unsetCurrencyCode();
      } else {
        setCurrencyCode((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case UID:
      return getUid();

    case NAME:
      return getName();

    case DESCRIPTION:
      return getDescription();

    case SERVICES:
      return getServices();

    case IPV4_ADDRESS:
      return getIpv4Address();

    case CURRENCY_CODE:
      return getCurrencyCode();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case UID:
      return isSetUid();
    case NAME:
      return isSetName();
    case DESCRIPTION:
      return isSetDescription();
    case SERVICES:
      return isSetServices();
    case IPV4_ADDRESS:
      return isSetIpv4Address();
    case CURRENCY_CODE:
      return isSetCurrencyCode();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Device)
      return this.equals((Device)that);
    return false;
  }

  public boolean equals(Device that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_uid = true && this.isSetUid();
    boolean that_present_uid = true && that.isSetUid();
    if (this_present_uid || that_present_uid) {
      if (!(this_present_uid && that_present_uid))
        return false;
      if (!this.uid.equals(that.uid))
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_description = true && this.isSetDescription();
    boolean that_present_description = true && that.isSetDescription();
    if (this_present_description || that_present_description) {
      if (!(this_present_description && that_present_description))
        return false;
      if (!this.description.equals(that.description))
        return false;
    }

    boolean this_present_services = true && this.isSetServices();
    boolean that_present_services = true && that.isSetServices();
    if (this_present_services || that_present_services) {
      if (!(this_present_services && that_present_services))
        return false;
      if (!this.services.equals(that.services))
        return false;
    }

    boolean this_present_ipv4Address = true && this.isSetIpv4Address();
    boolean that_present_ipv4Address = true && that.isSetIpv4Address();
    if (this_present_ipv4Address || that_present_ipv4Address) {
      if (!(this_present_ipv4Address && that_present_ipv4Address))
        return false;
      if (!this.ipv4Address.equals(that.ipv4Address))
        return false;
    }

    boolean this_present_currencyCode = true && this.isSetCurrencyCode();
    boolean that_present_currencyCode = true && that.isSetCurrencyCode();
    if (this_present_currencyCode || that_present_currencyCode) {
      if (!(this_present_currencyCode && that_present_currencyCode))
        return false;
      if (!this.currencyCode.equals(that.currencyCode))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetUid()) ? 131071 : 524287);
    if (isSetUid())
      hashCode = hashCode * 8191 + uid.hashCode();

    hashCode = hashCode * 8191 + ((isSetName()) ? 131071 : 524287);
    if (isSetName())
      hashCode = hashCode * 8191 + name.hashCode();

    hashCode = hashCode * 8191 + ((isSetDescription()) ? 131071 : 524287);
    if (isSetDescription())
      hashCode = hashCode * 8191 + description.hashCode();

    hashCode = hashCode * 8191 + ((isSetServices()) ? 131071 : 524287);
    if (isSetServices())
      hashCode = hashCode * 8191 + services.hashCode();

    hashCode = hashCode * 8191 + ((isSetIpv4Address()) ? 131071 : 524287);
    if (isSetIpv4Address())
      hashCode = hashCode * 8191 + ipv4Address.hashCode();

    hashCode = hashCode * 8191 + ((isSetCurrencyCode()) ? 131071 : 524287);
    if (isSetCurrencyCode())
      hashCode = hashCode * 8191 + currencyCode.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(Device other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetUid()).compareTo(other.isSetUid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uid, other.uid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDescription()).compareTo(other.isSetDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.description, other.description);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetServices()).compareTo(other.isSetServices());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServices()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.services, other.services);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIpv4Address()).compareTo(other.isSetIpv4Address());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIpv4Address()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ipv4Address, other.ipv4Address);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCurrencyCode()).compareTo(other.isSetCurrencyCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrencyCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currencyCode, other.currencyCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Device(");
    boolean first = true;

    sb.append("uid:");
    if (this.uid == null) {
      sb.append("null");
    } else {
      sb.append(this.uid);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("description:");
    if (this.description == null) {
      sb.append("null");
    } else {
      sb.append(this.description);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("services:");
    if (this.services == null) {
      sb.append("null");
    } else {
      sb.append(this.services);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("ipv4Address:");
    if (this.ipv4Address == null) {
      sb.append("null");
    } else {
      sb.append(this.ipv4Address);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("currencyCode:");
    if (this.currencyCode == null) {
      sb.append("null");
    } else {
      sb.append(this.currencyCode);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DeviceStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DeviceStandardScheme getScheme() {
      return new DeviceStandardScheme();
    }
  }

  private static class DeviceStandardScheme extends org.apache.thrift.scheme.StandardScheme<Device> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Device struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // UID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.uid = iprot.readString();
              struct.setUidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.description = iprot.readString();
              struct.setDescriptionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SERVICES
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map10 = iprot.readMapBegin();
                struct.services = new java.util.HashMap<java.lang.Integer,Service>(2*_map10.size);
                int _key11;
                Service _val12;
                for (int _i13 = 0; _i13 < _map10.size; ++_i13)
                {
                  _key11 = iprot.readI32();
                  _val12 = new Service();
                  _val12.read(iprot);
                  struct.services.put(_key11, _val12);
                }
                iprot.readMapEnd();
              }
              struct.setServicesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // IPV4_ADDRESS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.ipv4Address = iprot.readString();
              struct.setIpv4AddressIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // CURRENCY_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.currencyCode = iprot.readString();
              struct.setCurrencyCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Device struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.uid != null) {
        oprot.writeFieldBegin(UID_FIELD_DESC);
        oprot.writeString(struct.uid);
        oprot.writeFieldEnd();
      }
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      if (struct.services != null) {
        oprot.writeFieldBegin(SERVICES_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.STRUCT, struct.services.size()));
          for (java.util.Map.Entry<java.lang.Integer, Service> _iter14 : struct.services.entrySet())
          {
            oprot.writeI32(_iter14.getKey());
            _iter14.getValue().write(oprot);
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.ipv4Address != null) {
        oprot.writeFieldBegin(IPV4_ADDRESS_FIELD_DESC);
        oprot.writeString(struct.ipv4Address);
        oprot.writeFieldEnd();
      }
      if (struct.currencyCode != null) {
        oprot.writeFieldBegin(CURRENCY_CODE_FIELD_DESC);
        oprot.writeString(struct.currencyCode);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DeviceTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public DeviceTupleScheme getScheme() {
      return new DeviceTupleScheme();
    }
  }

  private static class DeviceTupleScheme extends org.apache.thrift.scheme.TupleScheme<Device> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Device struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetUid()) {
        optionals.set(0);
      }
      if (struct.isSetName()) {
        optionals.set(1);
      }
      if (struct.isSetDescription()) {
        optionals.set(2);
      }
      if (struct.isSetServices()) {
        optionals.set(3);
      }
      if (struct.isSetIpv4Address()) {
        optionals.set(4);
      }
      if (struct.isSetCurrencyCode()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetUid()) {
        oprot.writeString(struct.uid);
      }
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
      if (struct.isSetServices()) {
        {
          oprot.writeI32(struct.services.size());
          for (java.util.Map.Entry<java.lang.Integer, Service> _iter15 : struct.services.entrySet())
          {
            oprot.writeI32(_iter15.getKey());
            _iter15.getValue().write(oprot);
          }
        }
      }
      if (struct.isSetIpv4Address()) {
        oprot.writeString(struct.ipv4Address);
      }
      if (struct.isSetCurrencyCode()) {
        oprot.writeString(struct.currencyCode);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Device struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.uid = iprot.readString();
        struct.setUidIsSet(true);
      }
      if (incoming.get(1)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TMap _map16 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.I32, org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.services = new java.util.HashMap<java.lang.Integer,Service>(2*_map16.size);
          int _key17;
          Service _val18;
          for (int _i19 = 0; _i19 < _map16.size; ++_i19)
          {
            _key17 = iprot.readI32();
            _val18 = new Service();
            _val18.read(iprot);
            struct.services.put(_key17, _val18);
          }
        }
        struct.setServicesIsSet(true);
      }
      if (incoming.get(4)) {
        struct.ipv4Address = iprot.readString();
        struct.setIpv4AddressIsSet(true);
      }
      if (incoming.get(5)) {
        struct.currencyCode = iprot.readString();
        struct.setCurrencyCodeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

