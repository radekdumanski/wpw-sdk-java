/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.worldpay.innovation.wpwithin.rpc.types;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-11-27")
public class ServiceMessage implements org.apache.thrift.TBase<ServiceMessage, ServiceMessage._Fields>, java.io.Serializable, Cloneable, Comparable<ServiceMessage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ServiceMessage");

  private static final org.apache.thrift.protocol.TField DEVICE_DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("deviceDescription", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField HOSTNAME_FIELD_DESC = new org.apache.thrift.protocol.TField("hostname", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField PORT_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("portNumber", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField SERVER_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("serverId", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField URL_PREFIX_FIELD_DESC = new org.apache.thrift.protocol.TField("urlPrefix", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField SCHEME_FIELD_DESC = new org.apache.thrift.protocol.TField("scheme", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField DEVICE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("deviceName", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField SERVICE_TYPES_FIELD_DESC = new org.apache.thrift.protocol.TField("serviceTypes", org.apache.thrift.protocol.TType.SET, (short)8);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ServiceMessageStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ServiceMessageTupleSchemeFactory();

  public java.lang.String deviceDescription; // required
  public java.lang.String hostname; // required
  public int portNumber; // required
  public java.lang.String serverId; // required
  public java.lang.String urlPrefix; // required
  public java.lang.String scheme; // required
  public java.lang.String deviceName; // required
  public java.util.Set<java.lang.String> serviceTypes; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DEVICE_DESCRIPTION((short)1, "deviceDescription"),
    HOSTNAME((short)2, "hostname"),
    PORT_NUMBER((short)3, "portNumber"),
    SERVER_ID((short)4, "serverId"),
    URL_PREFIX((short)5, "urlPrefix"),
    SCHEME((short)6, "scheme"),
    DEVICE_NAME((short)7, "deviceName"),
    SERVICE_TYPES((short)8, "serviceTypes");

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
        case 1: // DEVICE_DESCRIPTION
          return DEVICE_DESCRIPTION;
        case 2: // HOSTNAME
          return HOSTNAME;
        case 3: // PORT_NUMBER
          return PORT_NUMBER;
        case 4: // SERVER_ID
          return SERVER_ID;
        case 5: // URL_PREFIX
          return URL_PREFIX;
        case 6: // SCHEME
          return SCHEME;
        case 7: // DEVICE_NAME
          return DEVICE_NAME;
        case 8: // SERVICE_TYPES
          return SERVICE_TYPES;
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
  private static final int __PORTNUMBER_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.SERVICE_TYPES};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DEVICE_DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("deviceDescription", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HOSTNAME, new org.apache.thrift.meta_data.FieldMetaData("hostname", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PORT_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("portNumber", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.SERVER_ID, new org.apache.thrift.meta_data.FieldMetaData("serverId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL_PREFIX, new org.apache.thrift.meta_data.FieldMetaData("urlPrefix", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SCHEME, new org.apache.thrift.meta_data.FieldMetaData("scheme", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DEVICE_NAME, new org.apache.thrift.meta_data.FieldMetaData("deviceName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.SERVICE_TYPES, new org.apache.thrift.meta_data.FieldMetaData("serviceTypes", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.SetMetaData(org.apache.thrift.protocol.TType.SET, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ServiceMessage.class, metaDataMap);
  }

  public ServiceMessage() {
  }

  public ServiceMessage(
    java.lang.String deviceDescription,
    java.lang.String hostname,
    int portNumber,
    java.lang.String serverId,
    java.lang.String urlPrefix,
    java.lang.String scheme,
    java.lang.String deviceName)
  {
    this();
    this.deviceDescription = deviceDescription;
    this.hostname = hostname;
    this.portNumber = portNumber;
    setPortNumberIsSet(true);
    this.serverId = serverId;
    this.urlPrefix = urlPrefix;
    this.scheme = scheme;
    this.deviceName = deviceName;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ServiceMessage(ServiceMessage other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetDeviceDescription()) {
      this.deviceDescription = other.deviceDescription;
    }
    if (other.isSetHostname()) {
      this.hostname = other.hostname;
    }
    this.portNumber = other.portNumber;
    if (other.isSetServerId()) {
      this.serverId = other.serverId;
    }
    if (other.isSetUrlPrefix()) {
      this.urlPrefix = other.urlPrefix;
    }
    if (other.isSetScheme()) {
      this.scheme = other.scheme;
    }
    if (other.isSetDeviceName()) {
      this.deviceName = other.deviceName;
    }
    if (other.isSetServiceTypes()) {
      java.util.Set<java.lang.String> __this__serviceTypes = new java.util.HashSet<java.lang.String>(other.serviceTypes);
      this.serviceTypes = __this__serviceTypes;
    }
  }

  public ServiceMessage deepCopy() {
    return new ServiceMessage(this);
  }

  @Override
  public void clear() {
    this.deviceDescription = null;
    this.hostname = null;
    setPortNumberIsSet(false);
    this.portNumber = 0;
    this.serverId = null;
    this.urlPrefix = null;
    this.scheme = null;
    this.deviceName = null;
    this.serviceTypes = null;
  }

  public java.lang.String getDeviceDescription() {
    return this.deviceDescription;
  }

  public ServiceMessage setDeviceDescription(java.lang.String deviceDescription) {
    this.deviceDescription = deviceDescription;
    return this;
  }

  public void unsetDeviceDescription() {
    this.deviceDescription = null;
  }

  /** Returns true if field deviceDescription is set (has been assigned a value) and false otherwise */
  public boolean isSetDeviceDescription() {
    return this.deviceDescription != null;
  }

  public void setDeviceDescriptionIsSet(boolean value) {
    if (!value) {
      this.deviceDescription = null;
    }
  }

  public java.lang.String getHostname() {
    return this.hostname;
  }

  public ServiceMessage setHostname(java.lang.String hostname) {
    this.hostname = hostname;
    return this;
  }

  public void unsetHostname() {
    this.hostname = null;
  }

  /** Returns true if field hostname is set (has been assigned a value) and false otherwise */
  public boolean isSetHostname() {
    return this.hostname != null;
  }

  public void setHostnameIsSet(boolean value) {
    if (!value) {
      this.hostname = null;
    }
  }

  public int getPortNumber() {
    return this.portNumber;
  }

  public ServiceMessage setPortNumber(int portNumber) {
    this.portNumber = portNumber;
    setPortNumberIsSet(true);
    return this;
  }

  public void unsetPortNumber() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PORTNUMBER_ISSET_ID);
  }

  /** Returns true if field portNumber is set (has been assigned a value) and false otherwise */
  public boolean isSetPortNumber() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PORTNUMBER_ISSET_ID);
  }

  public void setPortNumberIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PORTNUMBER_ISSET_ID, value);
  }

  public java.lang.String getServerId() {
    return this.serverId;
  }

  public ServiceMessage setServerId(java.lang.String serverId) {
    this.serverId = serverId;
    return this;
  }

  public void unsetServerId() {
    this.serverId = null;
  }

  /** Returns true if field serverId is set (has been assigned a value) and false otherwise */
  public boolean isSetServerId() {
    return this.serverId != null;
  }

  public void setServerIdIsSet(boolean value) {
    if (!value) {
      this.serverId = null;
    }
  }

  public java.lang.String getUrlPrefix() {
    return this.urlPrefix;
  }

  public ServiceMessage setUrlPrefix(java.lang.String urlPrefix) {
    this.urlPrefix = urlPrefix;
    return this;
  }

  public void unsetUrlPrefix() {
    this.urlPrefix = null;
  }

  /** Returns true if field urlPrefix is set (has been assigned a value) and false otherwise */
  public boolean isSetUrlPrefix() {
    return this.urlPrefix != null;
  }

  public void setUrlPrefixIsSet(boolean value) {
    if (!value) {
      this.urlPrefix = null;
    }
  }

  public java.lang.String getScheme() {
    return this.scheme;
  }

  public ServiceMessage setScheme(java.lang.String scheme) {
    this.scheme = scheme;
    return this;
  }

  public void unsetScheme() {
    this.scheme = null;
  }

  /** Returns true if field scheme is set (has been assigned a value) and false otherwise */
  public boolean isSetScheme() {
    return this.scheme != null;
  }

  public void setSchemeIsSet(boolean value) {
    if (!value) {
      this.scheme = null;
    }
  }

  public java.lang.String getDeviceName() {
    return this.deviceName;
  }

  public ServiceMessage setDeviceName(java.lang.String deviceName) {
    this.deviceName = deviceName;
    return this;
  }

  public void unsetDeviceName() {
    this.deviceName = null;
  }

  /** Returns true if field deviceName is set (has been assigned a value) and false otherwise */
  public boolean isSetDeviceName() {
    return this.deviceName != null;
  }

  public void setDeviceNameIsSet(boolean value) {
    if (!value) {
      this.deviceName = null;
    }
  }

  public int getServiceTypesSize() {
    return (this.serviceTypes == null) ? 0 : this.serviceTypes.size();
  }

  public java.util.Iterator<java.lang.String> getServiceTypesIterator() {
    return (this.serviceTypes == null) ? null : this.serviceTypes.iterator();
  }

  public void addToServiceTypes(java.lang.String elem) {
    if (this.serviceTypes == null) {
      this.serviceTypes = new java.util.HashSet<java.lang.String>();
    }
    this.serviceTypes.add(elem);
  }

  public java.util.Set<java.lang.String> getServiceTypes() {
    return this.serviceTypes;
  }

  public ServiceMessage setServiceTypes(java.util.Set<java.lang.String> serviceTypes) {
    this.serviceTypes = serviceTypes;
    return this;
  }

  public void unsetServiceTypes() {
    this.serviceTypes = null;
  }

  /** Returns true if field serviceTypes is set (has been assigned a value) and false otherwise */
  public boolean isSetServiceTypes() {
    return this.serviceTypes != null;
  }

  public void setServiceTypesIsSet(boolean value) {
    if (!value) {
      this.serviceTypes = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case DEVICE_DESCRIPTION:
      if (value == null) {
        unsetDeviceDescription();
      } else {
        setDeviceDescription((java.lang.String)value);
      }
      break;

    case HOSTNAME:
      if (value == null) {
        unsetHostname();
      } else {
        setHostname((java.lang.String)value);
      }
      break;

    case PORT_NUMBER:
      if (value == null) {
        unsetPortNumber();
      } else {
        setPortNumber((java.lang.Integer)value);
      }
      break;

    case SERVER_ID:
      if (value == null) {
        unsetServerId();
      } else {
        setServerId((java.lang.String)value);
      }
      break;

    case URL_PREFIX:
      if (value == null) {
        unsetUrlPrefix();
      } else {
        setUrlPrefix((java.lang.String)value);
      }
      break;

    case SCHEME:
      if (value == null) {
        unsetScheme();
      } else {
        setScheme((java.lang.String)value);
      }
      break;

    case DEVICE_NAME:
      if (value == null) {
        unsetDeviceName();
      } else {
        setDeviceName((java.lang.String)value);
      }
      break;

    case SERVICE_TYPES:
      if (value == null) {
        unsetServiceTypes();
      } else {
        setServiceTypes((java.util.Set<java.lang.String>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case DEVICE_DESCRIPTION:
      return getDeviceDescription();

    case HOSTNAME:
      return getHostname();

    case PORT_NUMBER:
      return getPortNumber();

    case SERVER_ID:
      return getServerId();

    case URL_PREFIX:
      return getUrlPrefix();

    case SCHEME:
      return getScheme();

    case DEVICE_NAME:
      return getDeviceName();

    case SERVICE_TYPES:
      return getServiceTypes();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case DEVICE_DESCRIPTION:
      return isSetDeviceDescription();
    case HOSTNAME:
      return isSetHostname();
    case PORT_NUMBER:
      return isSetPortNumber();
    case SERVER_ID:
      return isSetServerId();
    case URL_PREFIX:
      return isSetUrlPrefix();
    case SCHEME:
      return isSetScheme();
    case DEVICE_NAME:
      return isSetDeviceName();
    case SERVICE_TYPES:
      return isSetServiceTypes();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ServiceMessage)
      return this.equals((ServiceMessage)that);
    return false;
  }

  public boolean equals(ServiceMessage that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_deviceDescription = true && this.isSetDeviceDescription();
    boolean that_present_deviceDescription = true && that.isSetDeviceDescription();
    if (this_present_deviceDescription || that_present_deviceDescription) {
      if (!(this_present_deviceDescription && that_present_deviceDescription))
        return false;
      if (!this.deviceDescription.equals(that.deviceDescription))
        return false;
    }

    boolean this_present_hostname = true && this.isSetHostname();
    boolean that_present_hostname = true && that.isSetHostname();
    if (this_present_hostname || that_present_hostname) {
      if (!(this_present_hostname && that_present_hostname))
        return false;
      if (!this.hostname.equals(that.hostname))
        return false;
    }

    boolean this_present_portNumber = true;
    boolean that_present_portNumber = true;
    if (this_present_portNumber || that_present_portNumber) {
      if (!(this_present_portNumber && that_present_portNumber))
        return false;
      if (this.portNumber != that.portNumber)
        return false;
    }

    boolean this_present_serverId = true && this.isSetServerId();
    boolean that_present_serverId = true && that.isSetServerId();
    if (this_present_serverId || that_present_serverId) {
      if (!(this_present_serverId && that_present_serverId))
        return false;
      if (!this.serverId.equals(that.serverId))
        return false;
    }

    boolean this_present_urlPrefix = true && this.isSetUrlPrefix();
    boolean that_present_urlPrefix = true && that.isSetUrlPrefix();
    if (this_present_urlPrefix || that_present_urlPrefix) {
      if (!(this_present_urlPrefix && that_present_urlPrefix))
        return false;
      if (!this.urlPrefix.equals(that.urlPrefix))
        return false;
    }

    boolean this_present_scheme = true && this.isSetScheme();
    boolean that_present_scheme = true && that.isSetScheme();
    if (this_present_scheme || that_present_scheme) {
      if (!(this_present_scheme && that_present_scheme))
        return false;
      if (!this.scheme.equals(that.scheme))
        return false;
    }

    boolean this_present_deviceName = true && this.isSetDeviceName();
    boolean that_present_deviceName = true && that.isSetDeviceName();
    if (this_present_deviceName || that_present_deviceName) {
      if (!(this_present_deviceName && that_present_deviceName))
        return false;
      if (!this.deviceName.equals(that.deviceName))
        return false;
    }

    boolean this_present_serviceTypes = true && this.isSetServiceTypes();
    boolean that_present_serviceTypes = true && that.isSetServiceTypes();
    if (this_present_serviceTypes || that_present_serviceTypes) {
      if (!(this_present_serviceTypes && that_present_serviceTypes))
        return false;
      if (!this.serviceTypes.equals(that.serviceTypes))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetDeviceDescription()) ? 131071 : 524287);
    if (isSetDeviceDescription())
      hashCode = hashCode * 8191 + deviceDescription.hashCode();

    hashCode = hashCode * 8191 + ((isSetHostname()) ? 131071 : 524287);
    if (isSetHostname())
      hashCode = hashCode * 8191 + hostname.hashCode();

    hashCode = hashCode * 8191 + portNumber;

    hashCode = hashCode * 8191 + ((isSetServerId()) ? 131071 : 524287);
    if (isSetServerId())
      hashCode = hashCode * 8191 + serverId.hashCode();

    hashCode = hashCode * 8191 + ((isSetUrlPrefix()) ? 131071 : 524287);
    if (isSetUrlPrefix())
      hashCode = hashCode * 8191 + urlPrefix.hashCode();

    hashCode = hashCode * 8191 + ((isSetScheme()) ? 131071 : 524287);
    if (isSetScheme())
      hashCode = hashCode * 8191 + scheme.hashCode();

    hashCode = hashCode * 8191 + ((isSetDeviceName()) ? 131071 : 524287);
    if (isSetDeviceName())
      hashCode = hashCode * 8191 + deviceName.hashCode();

    hashCode = hashCode * 8191 + ((isSetServiceTypes()) ? 131071 : 524287);
    if (isSetServiceTypes())
      hashCode = hashCode * 8191 + serviceTypes.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ServiceMessage other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetDeviceDescription()).compareTo(other.isSetDeviceDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDeviceDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.deviceDescription, other.deviceDescription);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetHostname()).compareTo(other.isSetHostname());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHostname()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hostname, other.hostname);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPortNumber()).compareTo(other.isSetPortNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPortNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.portNumber, other.portNumber);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetServerId()).compareTo(other.isSetServerId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServerId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serverId, other.serverId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUrlPrefix()).compareTo(other.isSetUrlPrefix());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrlPrefix()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.urlPrefix, other.urlPrefix);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetScheme()).compareTo(other.isSetScheme());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetScheme()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.scheme, other.scheme);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDeviceName()).compareTo(other.isSetDeviceName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDeviceName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.deviceName, other.deviceName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetServiceTypes()).compareTo(other.isSetServiceTypes());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetServiceTypes()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.serviceTypes, other.serviceTypes);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ServiceMessage(");
    boolean first = true;

    sb.append("deviceDescription:");
    if (this.deviceDescription == null) {
      sb.append("null");
    } else {
      sb.append(this.deviceDescription);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("hostname:");
    if (this.hostname == null) {
      sb.append("null");
    } else {
      sb.append(this.hostname);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("portNumber:");
    sb.append(this.portNumber);
    first = false;
    if (!first) sb.append(", ");
    sb.append("serverId:");
    if (this.serverId == null) {
      sb.append("null");
    } else {
      sb.append(this.serverId);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("urlPrefix:");
    if (this.urlPrefix == null) {
      sb.append("null");
    } else {
      sb.append(this.urlPrefix);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("scheme:");
    if (this.scheme == null) {
      sb.append("null");
    } else {
      sb.append(this.scheme);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("deviceName:");
    if (this.deviceName == null) {
      sb.append("null");
    } else {
      sb.append(this.deviceName);
    }
    first = false;
    if (isSetServiceTypes()) {
      if (!first) sb.append(", ");
      sb.append("serviceTypes:");
      if (this.serviceTypes == null) {
        sb.append("null");
      } else {
        sb.append(this.serviceTypes);
      }
      first = false;
    }
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ServiceMessageStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ServiceMessageStandardScheme getScheme() {
      return new ServiceMessageStandardScheme();
    }
  }

  private static class ServiceMessageStandardScheme extends org.apache.thrift.scheme.StandardScheme<ServiceMessage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ServiceMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DEVICE_DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.deviceDescription = iprot.readString();
              struct.setDeviceDescriptionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HOSTNAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.hostname = iprot.readString();
              struct.setHostnameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // PORT_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.portNumber = iprot.readI32();
              struct.setPortNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // SERVER_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.serverId = iprot.readString();
              struct.setServerIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // URL_PREFIX
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.urlPrefix = iprot.readString();
              struct.setUrlPrefixIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // SCHEME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.scheme = iprot.readString();
              struct.setSchemeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // DEVICE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.deviceName = iprot.readString();
              struct.setDeviceNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // SERVICE_TYPES
            if (schemeField.type == org.apache.thrift.protocol.TType.SET) {
              {
                org.apache.thrift.protocol.TSet _set20 = iprot.readSetBegin();
                struct.serviceTypes = new java.util.HashSet<java.lang.String>(2*_set20.size);
                java.lang.String _elem21;
                for (int _i22 = 0; _i22 < _set20.size; ++_i22)
                {
                  _elem21 = iprot.readString();
                  struct.serviceTypes.add(_elem21);
                }
                iprot.readSetEnd();
              }
              struct.setServiceTypesIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ServiceMessage struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.deviceDescription != null) {
        oprot.writeFieldBegin(DEVICE_DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.deviceDescription);
        oprot.writeFieldEnd();
      }
      if (struct.hostname != null) {
        oprot.writeFieldBegin(HOSTNAME_FIELD_DESC);
        oprot.writeString(struct.hostname);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PORT_NUMBER_FIELD_DESC);
      oprot.writeI32(struct.portNumber);
      oprot.writeFieldEnd();
      if (struct.serverId != null) {
        oprot.writeFieldBegin(SERVER_ID_FIELD_DESC);
        oprot.writeString(struct.serverId);
        oprot.writeFieldEnd();
      }
      if (struct.urlPrefix != null) {
        oprot.writeFieldBegin(URL_PREFIX_FIELD_DESC);
        oprot.writeString(struct.urlPrefix);
        oprot.writeFieldEnd();
      }
      if (struct.scheme != null) {
        oprot.writeFieldBegin(SCHEME_FIELD_DESC);
        oprot.writeString(struct.scheme);
        oprot.writeFieldEnd();
      }
      if (struct.deviceName != null) {
        oprot.writeFieldBegin(DEVICE_NAME_FIELD_DESC);
        oprot.writeString(struct.deviceName);
        oprot.writeFieldEnd();
      }
      if (struct.serviceTypes != null) {
        if (struct.isSetServiceTypes()) {
          oprot.writeFieldBegin(SERVICE_TYPES_FIELD_DESC);
          {
            oprot.writeSetBegin(new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, struct.serviceTypes.size()));
            for (java.lang.String _iter23 : struct.serviceTypes)
            {
              oprot.writeString(_iter23);
            }
            oprot.writeSetEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ServiceMessageTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ServiceMessageTupleScheme getScheme() {
      return new ServiceMessageTupleScheme();
    }
  }

  private static class ServiceMessageTupleScheme extends org.apache.thrift.scheme.TupleScheme<ServiceMessage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ServiceMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDeviceDescription()) {
        optionals.set(0);
      }
      if (struct.isSetHostname()) {
        optionals.set(1);
      }
      if (struct.isSetPortNumber()) {
        optionals.set(2);
      }
      if (struct.isSetServerId()) {
        optionals.set(3);
      }
      if (struct.isSetUrlPrefix()) {
        optionals.set(4);
      }
      if (struct.isSetScheme()) {
        optionals.set(5);
      }
      if (struct.isSetDeviceName()) {
        optionals.set(6);
      }
      if (struct.isSetServiceTypes()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetDeviceDescription()) {
        oprot.writeString(struct.deviceDescription);
      }
      if (struct.isSetHostname()) {
        oprot.writeString(struct.hostname);
      }
      if (struct.isSetPortNumber()) {
        oprot.writeI32(struct.portNumber);
      }
      if (struct.isSetServerId()) {
        oprot.writeString(struct.serverId);
      }
      if (struct.isSetUrlPrefix()) {
        oprot.writeString(struct.urlPrefix);
      }
      if (struct.isSetScheme()) {
        oprot.writeString(struct.scheme);
      }
      if (struct.isSetDeviceName()) {
        oprot.writeString(struct.deviceName);
      }
      if (struct.isSetServiceTypes()) {
        {
          oprot.writeI32(struct.serviceTypes.size());
          for (java.lang.String _iter24 : struct.serviceTypes)
          {
            oprot.writeString(_iter24);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ServiceMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.deviceDescription = iprot.readString();
        struct.setDeviceDescriptionIsSet(true);
      }
      if (incoming.get(1)) {
        struct.hostname = iprot.readString();
        struct.setHostnameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.portNumber = iprot.readI32();
        struct.setPortNumberIsSet(true);
      }
      if (incoming.get(3)) {
        struct.serverId = iprot.readString();
        struct.setServerIdIsSet(true);
      }
      if (incoming.get(4)) {
        struct.urlPrefix = iprot.readString();
        struct.setUrlPrefixIsSet(true);
      }
      if (incoming.get(5)) {
        struct.scheme = iprot.readString();
        struct.setSchemeIsSet(true);
      }
      if (incoming.get(6)) {
        struct.deviceName = iprot.readString();
        struct.setDeviceNameIsSet(true);
      }
      if (incoming.get(7)) {
        {
          org.apache.thrift.protocol.TSet _set25 = new org.apache.thrift.protocol.TSet(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.serviceTypes = new java.util.HashSet<java.lang.String>(2*_set25.size);
          java.lang.String _elem26;
          for (int _i27 = 0; _i27 < _set25.size; ++_i27)
          {
            _elem26 = iprot.readString();
            struct.serviceTypes.add(_elem26);
          }
        }
        struct.setServiceTypesIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

