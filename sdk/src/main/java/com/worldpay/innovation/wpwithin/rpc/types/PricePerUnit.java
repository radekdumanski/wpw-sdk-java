/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.worldpay.innovation.wpwithin.rpc.types;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-11-27")
public class PricePerUnit implements org.apache.thrift.TBase<PricePerUnit, PricePerUnit._Fields>, java.io.Serializable, Cloneable, Comparable<PricePerUnit> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PricePerUnit");

  private static final org.apache.thrift.protocol.TField AMOUNT_FIELD_DESC = new org.apache.thrift.protocol.TField("amount", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CURRENCY_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("currencyCode", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new PricePerUnitStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new PricePerUnitTupleSchemeFactory();

  public int amount; // required
  public java.lang.String currencyCode; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    AMOUNT((short)1, "amount"),
    CURRENCY_CODE((short)2, "currencyCode");

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
        case 1: // AMOUNT
          return AMOUNT;
        case 2: // CURRENCY_CODE
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
  private static final int __AMOUNT_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.AMOUNT, new org.apache.thrift.meta_data.FieldMetaData("amount", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CURRENCY_CODE, new org.apache.thrift.meta_data.FieldMetaData("currencyCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PricePerUnit.class, metaDataMap);
  }

  public PricePerUnit() {
  }

  public PricePerUnit(
    int amount,
    java.lang.String currencyCode)
  {
    this();
    this.amount = amount;
    setAmountIsSet(true);
    this.currencyCode = currencyCode;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PricePerUnit(PricePerUnit other) {
    __isset_bitfield = other.__isset_bitfield;
    this.amount = other.amount;
    if (other.isSetCurrencyCode()) {
      this.currencyCode = other.currencyCode;
    }
  }

  public PricePerUnit deepCopy() {
    return new PricePerUnit(this);
  }

  @Override
  public void clear() {
    setAmountIsSet(false);
    this.amount = 0;
    this.currencyCode = null;
  }

  public int getAmount() {
    return this.amount;
  }

  public PricePerUnit setAmount(int amount) {
    this.amount = amount;
    setAmountIsSet(true);
    return this;
  }

  public void unsetAmount() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AMOUNT_ISSET_ID);
  }

  /** Returns true if field amount is set (has been assigned a value) and false otherwise */
  public boolean isSetAmount() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AMOUNT_ISSET_ID);
  }

  public void setAmountIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AMOUNT_ISSET_ID, value);
  }

  public java.lang.String getCurrencyCode() {
    return this.currencyCode;
  }

  public PricePerUnit setCurrencyCode(java.lang.String currencyCode) {
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
    case AMOUNT:
      if (value == null) {
        unsetAmount();
      } else {
        setAmount((java.lang.Integer)value);
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
    case AMOUNT:
      return getAmount();

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
    case AMOUNT:
      return isSetAmount();
    case CURRENCY_CODE:
      return isSetCurrencyCode();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof PricePerUnit)
      return this.equals((PricePerUnit)that);
    return false;
  }

  public boolean equals(PricePerUnit that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_amount = true;
    boolean that_present_amount = true;
    if (this_present_amount || that_present_amount) {
      if (!(this_present_amount && that_present_amount))
        return false;
      if (this.amount != that.amount)
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

    hashCode = hashCode * 8191 + amount;

    hashCode = hashCode * 8191 + ((isSetCurrencyCode()) ? 131071 : 524287);
    if (isSetCurrencyCode())
      hashCode = hashCode * 8191 + currencyCode.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(PricePerUnit other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAmount()).compareTo(other.isSetAmount());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAmount()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.amount, other.amount);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("PricePerUnit(");
    boolean first = true;

    sb.append("amount:");
    sb.append(this.amount);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class PricePerUnitStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public PricePerUnitStandardScheme getScheme() {
      return new PricePerUnitStandardScheme();
    }
  }

  private static class PricePerUnitStandardScheme extends org.apache.thrift.scheme.StandardScheme<PricePerUnit> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PricePerUnit struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // AMOUNT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.amount = iprot.readI32();
              struct.setAmountIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CURRENCY_CODE
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, PricePerUnit struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(AMOUNT_FIELD_DESC);
      oprot.writeI32(struct.amount);
      oprot.writeFieldEnd();
      if (struct.currencyCode != null) {
        oprot.writeFieldBegin(CURRENCY_CODE_FIELD_DESC);
        oprot.writeString(struct.currencyCode);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PricePerUnitTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public PricePerUnitTupleScheme getScheme() {
      return new PricePerUnitTupleScheme();
    }
  }

  private static class PricePerUnitTupleScheme extends org.apache.thrift.scheme.TupleScheme<PricePerUnit> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PricePerUnit struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAmount()) {
        optionals.set(0);
      }
      if (struct.isSetCurrencyCode()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetAmount()) {
        oprot.writeI32(struct.amount);
      }
      if (struct.isSetCurrencyCode()) {
        oprot.writeString(struct.currencyCode);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PricePerUnit struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.amount = iprot.readI32();
        struct.setAmountIsSet(true);
      }
      if (incoming.get(1)) {
        struct.currencyCode = iprot.readString();
        struct.setCurrencyCodeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

