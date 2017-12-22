/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.worldpay.innovation.wpwithin.rpc.types;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-12-20")
public class ServiceDeliveryToken implements org.apache.thrift.TBase<ServiceDeliveryToken, ServiceDeliveryToken._Fields>, java.io.Serializable, Cloneable, Comparable<ServiceDeliveryToken> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ServiceDeliveryToken");

  private static final org.apache.thrift.protocol.TField KEY_FIELD_DESC = new org.apache.thrift.protocol.TField("key", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ISSUED_FIELD_DESC = new org.apache.thrift.protocol.TField("issued", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField EXPIRY_FIELD_DESC = new org.apache.thrift.protocol.TField("expiry", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField REFUND_ON_EXPIRY_FIELD_DESC = new org.apache.thrift.protocol.TField("refundOnExpiry", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField SIGNATURE_FIELD_DESC = new org.apache.thrift.protocol.TField("signature", org.apache.thrift.protocol.TType.STRING, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ServiceDeliveryTokenStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ServiceDeliveryTokenTupleSchemeFactory();

  public java.lang.String key; // required
  public java.lang.String issued; // required
  public java.lang.String expiry; // required
  public boolean refundOnExpiry; // required
  public java.nio.ByteBuffer signature; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    KEY((short)1, "key"),
    ISSUED((short)2, "issued"),
    EXPIRY((short)3, "expiry"),
    REFUND_ON_EXPIRY((short)4, "refundOnExpiry"),
    SIGNATURE((short)5, "signature");

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
        case 1: // KEY
          return KEY;
        case 2: // ISSUED
          return ISSUED;
        case 3: // EXPIRY
          return EXPIRY;
        case 4: // REFUND_ON_EXPIRY
          return REFUND_ON_EXPIRY;
        case 5: // SIGNATURE
          return SIGNATURE;
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
  private static final int __REFUNDONEXPIRY_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.KEY, new org.apache.thrift.meta_data.FieldMetaData("key", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ISSUED, new org.apache.thrift.meta_data.FieldMetaData("issued", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXPIRY, new org.apache.thrift.meta_data.FieldMetaData("expiry", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.REFUND_ON_EXPIRY, new org.apache.thrift.meta_data.FieldMetaData("refundOnExpiry", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.SIGNATURE, new org.apache.thrift.meta_data.FieldMetaData("signature", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ServiceDeliveryToken.class, metaDataMap);
  }

  public ServiceDeliveryToken() {
  }

  public ServiceDeliveryToken(
    java.lang.String key,
    java.lang.String issued,
    java.lang.String expiry,
    boolean refundOnExpiry,
    java.nio.ByteBuffer signature)
  {
    this();
    this.key = key;
    this.issued = issued;
    this.expiry = expiry;
    this.refundOnExpiry = refundOnExpiry;
    setRefundOnExpiryIsSet(true);
    this.signature = org.apache.thrift.TBaseHelper.copyBinary(signature);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ServiceDeliveryToken(ServiceDeliveryToken other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetKey()) {
      this.key = other.key;
    }
    if (other.isSetIssued()) {
      this.issued = other.issued;
    }
    if (other.isSetExpiry()) {
      this.expiry = other.expiry;
    }
    this.refundOnExpiry = other.refundOnExpiry;
    if (other.isSetSignature()) {
      this.signature = org.apache.thrift.TBaseHelper.copyBinary(other.signature);
    }
  }

  public ServiceDeliveryToken deepCopy() {
    return new ServiceDeliveryToken(this);
  }

  @Override
  public void clear() {
    this.key = null;
    this.issued = null;
    this.expiry = null;
    setRefundOnExpiryIsSet(false);
    this.refundOnExpiry = false;
    this.signature = null;
  }

  public java.lang.String getKey() {
    return this.key;
  }

  public ServiceDeliveryToken setKey(java.lang.String key) {
    this.key = key;
    return this;
  }

  public void unsetKey() {
    this.key = null;
  }

  /** Returns true if field key is set (has been assigned a value) and false otherwise */
  public boolean isSetKey() {
    return this.key != null;
  }

  public void setKeyIsSet(boolean value) {
    if (!value) {
      this.key = null;
    }
  }

  public java.lang.String getIssued() {
    return this.issued;
  }

  public ServiceDeliveryToken setIssued(java.lang.String issued) {
    this.issued = issued;
    return this;
  }

  public void unsetIssued() {
    this.issued = null;
  }

  /** Returns true if field issued is set (has been assigned a value) and false otherwise */
  public boolean isSetIssued() {
    return this.issued != null;
  }

  public void setIssuedIsSet(boolean value) {
    if (!value) {
      this.issued = null;
    }
  }

  public java.lang.String getExpiry() {
    return this.expiry;
  }

  public ServiceDeliveryToken setExpiry(java.lang.String expiry) {
    this.expiry = expiry;
    return this;
  }

  public void unsetExpiry() {
    this.expiry = null;
  }

  /** Returns true if field expiry is set (has been assigned a value) and false otherwise */
  public boolean isSetExpiry() {
    return this.expiry != null;
  }

  public void setExpiryIsSet(boolean value) {
    if (!value) {
      this.expiry = null;
    }
  }

  public boolean isRefundOnExpiry() {
    return this.refundOnExpiry;
  }

  public ServiceDeliveryToken setRefundOnExpiry(boolean refundOnExpiry) {
    this.refundOnExpiry = refundOnExpiry;
    setRefundOnExpiryIsSet(true);
    return this;
  }

  public void unsetRefundOnExpiry() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __REFUNDONEXPIRY_ISSET_ID);
  }

  /** Returns true if field refundOnExpiry is set (has been assigned a value) and false otherwise */
  public boolean isSetRefundOnExpiry() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __REFUNDONEXPIRY_ISSET_ID);
  }

  public void setRefundOnExpiryIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __REFUNDONEXPIRY_ISSET_ID, value);
  }

  public byte[] getSignature() {
    setSignature(org.apache.thrift.TBaseHelper.rightSize(signature));
    return signature == null ? null : signature.array();
  }

  public java.nio.ByteBuffer bufferForSignature() {
    return org.apache.thrift.TBaseHelper.copyBinary(signature);
  }

  public ServiceDeliveryToken setSignature(byte[] signature) {
    this.signature = signature == null ? (java.nio.ByteBuffer)null : java.nio.ByteBuffer.wrap(signature.clone());
    return this;
  }

  public ServiceDeliveryToken setSignature(java.nio.ByteBuffer signature) {
    this.signature = org.apache.thrift.TBaseHelper.copyBinary(signature);
    return this;
  }

  public void unsetSignature() {
    this.signature = null;
  }

  /** Returns true if field signature is set (has been assigned a value) and false otherwise */
  public boolean isSetSignature() {
    return this.signature != null;
  }

  public void setSignatureIsSet(boolean value) {
    if (!value) {
      this.signature = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case KEY:
      if (value == null) {
        unsetKey();
      } else {
        setKey((java.lang.String)value);
      }
      break;

    case ISSUED:
      if (value == null) {
        unsetIssued();
      } else {
        setIssued((java.lang.String)value);
      }
      break;

    case EXPIRY:
      if (value == null) {
        unsetExpiry();
      } else {
        setExpiry((java.lang.String)value);
      }
      break;

    case REFUND_ON_EXPIRY:
      if (value == null) {
        unsetRefundOnExpiry();
      } else {
        setRefundOnExpiry((java.lang.Boolean)value);
      }
      break;

    case SIGNATURE:
      if (value == null) {
        unsetSignature();
      } else {
        if (value instanceof byte[]) {
          setSignature((byte[])value);
        } else {
          setSignature((java.nio.ByteBuffer)value);
        }
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case KEY:
      return getKey();

    case ISSUED:
      return getIssued();

    case EXPIRY:
      return getExpiry();

    case REFUND_ON_EXPIRY:
      return isRefundOnExpiry();

    case SIGNATURE:
      return getSignature();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case KEY:
      return isSetKey();
    case ISSUED:
      return isSetIssued();
    case EXPIRY:
      return isSetExpiry();
    case REFUND_ON_EXPIRY:
      return isSetRefundOnExpiry();
    case SIGNATURE:
      return isSetSignature();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ServiceDeliveryToken)
      return this.equals((ServiceDeliveryToken)that);
    return false;
  }

  public boolean equals(ServiceDeliveryToken that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_key = true && this.isSetKey();
    boolean that_present_key = true && that.isSetKey();
    if (this_present_key || that_present_key) {
      if (!(this_present_key && that_present_key))
        return false;
      if (!this.key.equals(that.key))
        return false;
    }

    boolean this_present_issued = true && this.isSetIssued();
    boolean that_present_issued = true && that.isSetIssued();
    if (this_present_issued || that_present_issued) {
      if (!(this_present_issued && that_present_issued))
        return false;
      if (!this.issued.equals(that.issued))
        return false;
    }

    boolean this_present_expiry = true && this.isSetExpiry();
    boolean that_present_expiry = true && that.isSetExpiry();
    if (this_present_expiry || that_present_expiry) {
      if (!(this_present_expiry && that_present_expiry))
        return false;
      if (!this.expiry.equals(that.expiry))
        return false;
    }

    boolean this_present_refundOnExpiry = true;
    boolean that_present_refundOnExpiry = true;
    if (this_present_refundOnExpiry || that_present_refundOnExpiry) {
      if (!(this_present_refundOnExpiry && that_present_refundOnExpiry))
        return false;
      if (this.refundOnExpiry != that.refundOnExpiry)
        return false;
    }

    boolean this_present_signature = true && this.isSetSignature();
    boolean that_present_signature = true && that.isSetSignature();
    if (this_present_signature || that_present_signature) {
      if (!(this_present_signature && that_present_signature))
        return false;
      if (!this.signature.equals(that.signature))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetKey()) ? 131071 : 524287);
    if (isSetKey())
      hashCode = hashCode * 8191 + key.hashCode();

    hashCode = hashCode * 8191 + ((isSetIssued()) ? 131071 : 524287);
    if (isSetIssued())
      hashCode = hashCode * 8191 + issued.hashCode();

    hashCode = hashCode * 8191 + ((isSetExpiry()) ? 131071 : 524287);
    if (isSetExpiry())
      hashCode = hashCode * 8191 + expiry.hashCode();

    hashCode = hashCode * 8191 + ((refundOnExpiry) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetSignature()) ? 131071 : 524287);
    if (isSetSignature())
      hashCode = hashCode * 8191 + signature.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ServiceDeliveryToken other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetKey()).compareTo(other.isSetKey());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetKey()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.key, other.key);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIssued()).compareTo(other.isSetIssued());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIssued()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.issued, other.issued);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetExpiry()).compareTo(other.isSetExpiry());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExpiry()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.expiry, other.expiry);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRefundOnExpiry()).compareTo(other.isSetRefundOnExpiry());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRefundOnExpiry()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.refundOnExpiry, other.refundOnExpiry);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSignature()).compareTo(other.isSetSignature());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSignature()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.signature, other.signature);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ServiceDeliveryToken(");
    boolean first = true;

    sb.append("key:");
    if (this.key == null) {
      sb.append("null");
    } else {
      sb.append(this.key);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("issued:");
    if (this.issued == null) {
      sb.append("null");
    } else {
      sb.append(this.issued);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("expiry:");
    if (this.expiry == null) {
      sb.append("null");
    } else {
      sb.append(this.expiry);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("refundOnExpiry:");
    sb.append(this.refundOnExpiry);
    first = false;
    if (!first) sb.append(", ");
    sb.append("signature:");
    if (this.signature == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.signature, sb);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ServiceDeliveryTokenStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ServiceDeliveryTokenStandardScheme getScheme() {
      return new ServiceDeliveryTokenStandardScheme();
    }
  }

  private static class ServiceDeliveryTokenStandardScheme extends org.apache.thrift.scheme.StandardScheme<ServiceDeliveryToken> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ServiceDeliveryToken struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // KEY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.key = iprot.readString();
              struct.setKeyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ISSUED
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.issued = iprot.readString();
              struct.setIssuedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXPIRY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.expiry = iprot.readString();
              struct.setExpiryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // REFUND_ON_EXPIRY
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.refundOnExpiry = iprot.readBool();
              struct.setRefundOnExpiryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // SIGNATURE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.signature = iprot.readBinary();
              struct.setSignatureIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ServiceDeliveryToken struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.key != null) {
        oprot.writeFieldBegin(KEY_FIELD_DESC);
        oprot.writeString(struct.key);
        oprot.writeFieldEnd();
      }
      if (struct.issued != null) {
        oprot.writeFieldBegin(ISSUED_FIELD_DESC);
        oprot.writeString(struct.issued);
        oprot.writeFieldEnd();
      }
      if (struct.expiry != null) {
        oprot.writeFieldBegin(EXPIRY_FIELD_DESC);
        oprot.writeString(struct.expiry);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(REFUND_ON_EXPIRY_FIELD_DESC);
      oprot.writeBool(struct.refundOnExpiry);
      oprot.writeFieldEnd();
      if (struct.signature != null) {
        oprot.writeFieldBegin(SIGNATURE_FIELD_DESC);
        oprot.writeBinary(struct.signature);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ServiceDeliveryTokenTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ServiceDeliveryTokenTupleScheme getScheme() {
      return new ServiceDeliveryTokenTupleScheme();
    }
  }

  private static class ServiceDeliveryTokenTupleScheme extends org.apache.thrift.scheme.TupleScheme<ServiceDeliveryToken> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ServiceDeliveryToken struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetKey()) {
        optionals.set(0);
      }
      if (struct.isSetIssued()) {
        optionals.set(1);
      }
      if (struct.isSetExpiry()) {
        optionals.set(2);
      }
      if (struct.isSetRefundOnExpiry()) {
        optionals.set(3);
      }
      if (struct.isSetSignature()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetKey()) {
        oprot.writeString(struct.key);
      }
      if (struct.isSetIssued()) {
        oprot.writeString(struct.issued);
      }
      if (struct.isSetExpiry()) {
        oprot.writeString(struct.expiry);
      }
      if (struct.isSetRefundOnExpiry()) {
        oprot.writeBool(struct.refundOnExpiry);
      }
      if (struct.isSetSignature()) {
        oprot.writeBinary(struct.signature);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ServiceDeliveryToken struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.key = iprot.readString();
        struct.setKeyIsSet(true);
      }
      if (incoming.get(1)) {
        struct.issued = iprot.readString();
        struct.setIssuedIsSet(true);
      }
      if (incoming.get(2)) {
        struct.expiry = iprot.readString();
        struct.setExpiryIsSet(true);
      }
      if (incoming.get(3)) {
        struct.refundOnExpiry = iprot.readBool();
        struct.setRefundOnExpiryIsSet(true);
      }
      if (incoming.get(4)) {
        struct.signature = iprot.readBinary();
        struct.setSignatureIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

