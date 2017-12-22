/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.worldpay.innovation.wpwithin.rpc.types;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-12-20")
public class HCECard implements org.apache.thrift.TBase<HCECard, HCECard._Fields>, java.io.Serializable, Cloneable, Comparable<HCECard> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HCECard");

  private static final org.apache.thrift.protocol.TField FIRST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("firstName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField LAST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("lastName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField EXP_MONTH_FIELD_DESC = new org.apache.thrift.protocol.TField("expMonth", org.apache.thrift.protocol.TType.I32, (short)3);
  private static final org.apache.thrift.protocol.TField EXP_YEAR_FIELD_DESC = new org.apache.thrift.protocol.TField("expYear", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField CARD_NUMBER_FIELD_DESC = new org.apache.thrift.protocol.TField("cardNumber", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("type", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField CVC_FIELD_DESC = new org.apache.thrift.protocol.TField("cvc", org.apache.thrift.protocol.TType.STRING, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new HCECardStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new HCECardTupleSchemeFactory();

  public java.lang.String firstName; // required
  public java.lang.String lastName; // required
  public int expMonth; // required
  public int expYear; // required
  public java.lang.String cardNumber; // required
  public java.lang.String type; // required
  public java.lang.String cvc; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FIRST_NAME((short)1, "firstName"),
    LAST_NAME((short)2, "lastName"),
    EXP_MONTH((short)3, "expMonth"),
    EXP_YEAR((short)4, "expYear"),
    CARD_NUMBER((short)5, "cardNumber"),
    TYPE((short)6, "type"),
    CVC((short)7, "cvc");

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
        case 1: // FIRST_NAME
          return FIRST_NAME;
        case 2: // LAST_NAME
          return LAST_NAME;
        case 3: // EXP_MONTH
          return EXP_MONTH;
        case 4: // EXP_YEAR
          return EXP_YEAR;
        case 5: // CARD_NUMBER
          return CARD_NUMBER;
        case 6: // TYPE
          return TYPE;
        case 7: // CVC
          return CVC;
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
  private static final int __EXPMONTH_ISSET_ID = 0;
  private static final int __EXPYEAR_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FIRST_NAME, new org.apache.thrift.meta_data.FieldMetaData("firstName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.LAST_NAME, new org.apache.thrift.meta_data.FieldMetaData("lastName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.EXP_MONTH, new org.apache.thrift.meta_data.FieldMetaData("expMonth", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.EXP_YEAR, new org.apache.thrift.meta_data.FieldMetaData("expYear", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CARD_NUMBER, new org.apache.thrift.meta_data.FieldMetaData("cardNumber", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TYPE, new org.apache.thrift.meta_data.FieldMetaData("type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CVC, new org.apache.thrift.meta_data.FieldMetaData("cvc", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HCECard.class, metaDataMap);
  }

  public HCECard() {
  }

  public HCECard(
    java.lang.String firstName,
    java.lang.String lastName,
    int expMonth,
    int expYear,
    java.lang.String cardNumber,
    java.lang.String type,
    java.lang.String cvc)
  {
    this();
    this.firstName = firstName;
    this.lastName = lastName;
    this.expMonth = expMonth;
    setExpMonthIsSet(true);
    this.expYear = expYear;
    setExpYearIsSet(true);
    this.cardNumber = cardNumber;
    this.type = type;
    this.cvc = cvc;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public HCECard(HCECard other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetFirstName()) {
      this.firstName = other.firstName;
    }
    if (other.isSetLastName()) {
      this.lastName = other.lastName;
    }
    this.expMonth = other.expMonth;
    this.expYear = other.expYear;
    if (other.isSetCardNumber()) {
      this.cardNumber = other.cardNumber;
    }
    if (other.isSetType()) {
      this.type = other.type;
    }
    if (other.isSetCvc()) {
      this.cvc = other.cvc;
    }
  }

  public HCECard deepCopy() {
    return new HCECard(this);
  }

  @Override
  public void clear() {
    this.firstName = null;
    this.lastName = null;
    setExpMonthIsSet(false);
    this.expMonth = 0;
    setExpYearIsSet(false);
    this.expYear = 0;
    this.cardNumber = null;
    this.type = null;
    this.cvc = null;
  }

  public java.lang.String getFirstName() {
    return this.firstName;
  }

  public HCECard setFirstName(java.lang.String firstName) {
    this.firstName = firstName;
    return this;
  }

  public void unsetFirstName() {
    this.firstName = null;
  }

  /** Returns true if field firstName is set (has been assigned a value) and false otherwise */
  public boolean isSetFirstName() {
    return this.firstName != null;
  }

  public void setFirstNameIsSet(boolean value) {
    if (!value) {
      this.firstName = null;
    }
  }

  public java.lang.String getLastName() {
    return this.lastName;
  }

  public HCECard setLastName(java.lang.String lastName) {
    this.lastName = lastName;
    return this;
  }

  public void unsetLastName() {
    this.lastName = null;
  }

  /** Returns true if field lastName is set (has been assigned a value) and false otherwise */
  public boolean isSetLastName() {
    return this.lastName != null;
  }

  public void setLastNameIsSet(boolean value) {
    if (!value) {
      this.lastName = null;
    }
  }

  public int getExpMonth() {
    return this.expMonth;
  }

  public HCECard setExpMonth(int expMonth) {
    this.expMonth = expMonth;
    setExpMonthIsSet(true);
    return this;
  }

  public void unsetExpMonth() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __EXPMONTH_ISSET_ID);
  }

  /** Returns true if field expMonth is set (has been assigned a value) and false otherwise */
  public boolean isSetExpMonth() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __EXPMONTH_ISSET_ID);
  }

  public void setExpMonthIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __EXPMONTH_ISSET_ID, value);
  }

  public int getExpYear() {
    return this.expYear;
  }

  public HCECard setExpYear(int expYear) {
    this.expYear = expYear;
    setExpYearIsSet(true);
    return this;
  }

  public void unsetExpYear() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __EXPYEAR_ISSET_ID);
  }

  /** Returns true if field expYear is set (has been assigned a value) and false otherwise */
  public boolean isSetExpYear() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __EXPYEAR_ISSET_ID);
  }

  public void setExpYearIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __EXPYEAR_ISSET_ID, value);
  }

  public java.lang.String getCardNumber() {
    return this.cardNumber;
  }

  public HCECard setCardNumber(java.lang.String cardNumber) {
    this.cardNumber = cardNumber;
    return this;
  }

  public void unsetCardNumber() {
    this.cardNumber = null;
  }

  /** Returns true if field cardNumber is set (has been assigned a value) and false otherwise */
  public boolean isSetCardNumber() {
    return this.cardNumber != null;
  }

  public void setCardNumberIsSet(boolean value) {
    if (!value) {
      this.cardNumber = null;
    }
  }

  public java.lang.String getType() {
    return this.type;
  }

  public HCECard setType(java.lang.String type) {
    this.type = type;
    return this;
  }

  public void unsetType() {
    this.type = null;
  }

  /** Returns true if field type is set (has been assigned a value) and false otherwise */
  public boolean isSetType() {
    return this.type != null;
  }

  public void setTypeIsSet(boolean value) {
    if (!value) {
      this.type = null;
    }
  }

  public java.lang.String getCvc() {
    return this.cvc;
  }

  public HCECard setCvc(java.lang.String cvc) {
    this.cvc = cvc;
    return this;
  }

  public void unsetCvc() {
    this.cvc = null;
  }

  /** Returns true if field cvc is set (has been assigned a value) and false otherwise */
  public boolean isSetCvc() {
    return this.cvc != null;
  }

  public void setCvcIsSet(boolean value) {
    if (!value) {
      this.cvc = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case FIRST_NAME:
      if (value == null) {
        unsetFirstName();
      } else {
        setFirstName((java.lang.String)value);
      }
      break;

    case LAST_NAME:
      if (value == null) {
        unsetLastName();
      } else {
        setLastName((java.lang.String)value);
      }
      break;

    case EXP_MONTH:
      if (value == null) {
        unsetExpMonth();
      } else {
        setExpMonth((java.lang.Integer)value);
      }
      break;

    case EXP_YEAR:
      if (value == null) {
        unsetExpYear();
      } else {
        setExpYear((java.lang.Integer)value);
      }
      break;

    case CARD_NUMBER:
      if (value == null) {
        unsetCardNumber();
      } else {
        setCardNumber((java.lang.String)value);
      }
      break;

    case TYPE:
      if (value == null) {
        unsetType();
      } else {
        setType((java.lang.String)value);
      }
      break;

    case CVC:
      if (value == null) {
        unsetCvc();
      } else {
        setCvc((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case FIRST_NAME:
      return getFirstName();

    case LAST_NAME:
      return getLastName();

    case EXP_MONTH:
      return getExpMonth();

    case EXP_YEAR:
      return getExpYear();

    case CARD_NUMBER:
      return getCardNumber();

    case TYPE:
      return getType();

    case CVC:
      return getCvc();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case FIRST_NAME:
      return isSetFirstName();
    case LAST_NAME:
      return isSetLastName();
    case EXP_MONTH:
      return isSetExpMonth();
    case EXP_YEAR:
      return isSetExpYear();
    case CARD_NUMBER:
      return isSetCardNumber();
    case TYPE:
      return isSetType();
    case CVC:
      return isSetCvc();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof HCECard)
      return this.equals((HCECard)that);
    return false;
  }

  public boolean equals(HCECard that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_firstName = true && this.isSetFirstName();
    boolean that_present_firstName = true && that.isSetFirstName();
    if (this_present_firstName || that_present_firstName) {
      if (!(this_present_firstName && that_present_firstName))
        return false;
      if (!this.firstName.equals(that.firstName))
        return false;
    }

    boolean this_present_lastName = true && this.isSetLastName();
    boolean that_present_lastName = true && that.isSetLastName();
    if (this_present_lastName || that_present_lastName) {
      if (!(this_present_lastName && that_present_lastName))
        return false;
      if (!this.lastName.equals(that.lastName))
        return false;
    }

    boolean this_present_expMonth = true;
    boolean that_present_expMonth = true;
    if (this_present_expMonth || that_present_expMonth) {
      if (!(this_present_expMonth && that_present_expMonth))
        return false;
      if (this.expMonth != that.expMonth)
        return false;
    }

    boolean this_present_expYear = true;
    boolean that_present_expYear = true;
    if (this_present_expYear || that_present_expYear) {
      if (!(this_present_expYear && that_present_expYear))
        return false;
      if (this.expYear != that.expYear)
        return false;
    }

    boolean this_present_cardNumber = true && this.isSetCardNumber();
    boolean that_present_cardNumber = true && that.isSetCardNumber();
    if (this_present_cardNumber || that_present_cardNumber) {
      if (!(this_present_cardNumber && that_present_cardNumber))
        return false;
      if (!this.cardNumber.equals(that.cardNumber))
        return false;
    }

    boolean this_present_type = true && this.isSetType();
    boolean that_present_type = true && that.isSetType();
    if (this_present_type || that_present_type) {
      if (!(this_present_type && that_present_type))
        return false;
      if (!this.type.equals(that.type))
        return false;
    }

    boolean this_present_cvc = true && this.isSetCvc();
    boolean that_present_cvc = true && that.isSetCvc();
    if (this_present_cvc || that_present_cvc) {
      if (!(this_present_cvc && that_present_cvc))
        return false;
      if (!this.cvc.equals(that.cvc))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetFirstName()) ? 131071 : 524287);
    if (isSetFirstName())
      hashCode = hashCode * 8191 + firstName.hashCode();

    hashCode = hashCode * 8191 + ((isSetLastName()) ? 131071 : 524287);
    if (isSetLastName())
      hashCode = hashCode * 8191 + lastName.hashCode();

    hashCode = hashCode * 8191 + expMonth;

    hashCode = hashCode * 8191 + expYear;

    hashCode = hashCode * 8191 + ((isSetCardNumber()) ? 131071 : 524287);
    if (isSetCardNumber())
      hashCode = hashCode * 8191 + cardNumber.hashCode();

    hashCode = hashCode * 8191 + ((isSetType()) ? 131071 : 524287);
    if (isSetType())
      hashCode = hashCode * 8191 + type.hashCode();

    hashCode = hashCode * 8191 + ((isSetCvc()) ? 131071 : 524287);
    if (isSetCvc())
      hashCode = hashCode * 8191 + cvc.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(HCECard other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetFirstName()).compareTo(other.isSetFirstName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFirstName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.firstName, other.firstName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLastName()).compareTo(other.isSetLastName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLastName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.lastName, other.lastName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetExpMonth()).compareTo(other.isSetExpMonth());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExpMonth()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.expMonth, other.expMonth);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetExpYear()).compareTo(other.isSetExpYear());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExpYear()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.expYear, other.expYear);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCardNumber()).compareTo(other.isSetCardNumber());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCardNumber()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cardNumber, other.cardNumber);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetType()).compareTo(other.isSetType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.type, other.type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCvc()).compareTo(other.isSetCvc());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCvc()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cvc, other.cvc);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("HCECard(");
    boolean first = true;

    sb.append("firstName:");
    if (this.firstName == null) {
      sb.append("null");
    } else {
      sb.append(this.firstName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("lastName:");
    if (this.lastName == null) {
      sb.append("null");
    } else {
      sb.append(this.lastName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("expMonth:");
    sb.append(this.expMonth);
    first = false;
    if (!first) sb.append(", ");
    sb.append("expYear:");
    sb.append(this.expYear);
    first = false;
    if (!first) sb.append(", ");
    sb.append("cardNumber:");
    if (this.cardNumber == null) {
      sb.append("null");
    } else {
      sb.append(this.cardNumber);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("type:");
    if (this.type == null) {
      sb.append("null");
    } else {
      sb.append(this.type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("cvc:");
    if (this.cvc == null) {
      sb.append("null");
    } else {
      sb.append(this.cvc);
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

  private static class HCECardStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public HCECardStandardScheme getScheme() {
      return new HCECardStandardScheme();
    }
  }

  private static class HCECardStandardScheme extends org.apache.thrift.scheme.StandardScheme<HCECard> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, HCECard struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FIRST_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.firstName = iprot.readString();
              struct.setFirstNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LAST_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.lastName = iprot.readString();
              struct.setLastNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXP_MONTH
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.expMonth = iprot.readI32();
              struct.setExpMonthIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // EXP_YEAR
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.expYear = iprot.readI32();
              struct.setExpYearIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // CARD_NUMBER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.cardNumber = iprot.readString();
              struct.setCardNumberIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.type = iprot.readString();
              struct.setTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // CVC
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.cvc = iprot.readString();
              struct.setCvcIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, HCECard struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.firstName != null) {
        oprot.writeFieldBegin(FIRST_NAME_FIELD_DESC);
        oprot.writeString(struct.firstName);
        oprot.writeFieldEnd();
      }
      if (struct.lastName != null) {
        oprot.writeFieldBegin(LAST_NAME_FIELD_DESC);
        oprot.writeString(struct.lastName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(EXP_MONTH_FIELD_DESC);
      oprot.writeI32(struct.expMonth);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(EXP_YEAR_FIELD_DESC);
      oprot.writeI32(struct.expYear);
      oprot.writeFieldEnd();
      if (struct.cardNumber != null) {
        oprot.writeFieldBegin(CARD_NUMBER_FIELD_DESC);
        oprot.writeString(struct.cardNumber);
        oprot.writeFieldEnd();
      }
      if (struct.type != null) {
        oprot.writeFieldBegin(TYPE_FIELD_DESC);
        oprot.writeString(struct.type);
        oprot.writeFieldEnd();
      }
      if (struct.cvc != null) {
        oprot.writeFieldBegin(CVC_FIELD_DESC);
        oprot.writeString(struct.cvc);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class HCECardTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public HCECardTupleScheme getScheme() {
      return new HCECardTupleScheme();
    }
  }

  private static class HCECardTupleScheme extends org.apache.thrift.scheme.TupleScheme<HCECard> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, HCECard struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetFirstName()) {
        optionals.set(0);
      }
      if (struct.isSetLastName()) {
        optionals.set(1);
      }
      if (struct.isSetExpMonth()) {
        optionals.set(2);
      }
      if (struct.isSetExpYear()) {
        optionals.set(3);
      }
      if (struct.isSetCardNumber()) {
        optionals.set(4);
      }
      if (struct.isSetType()) {
        optionals.set(5);
      }
      if (struct.isSetCvc()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetFirstName()) {
        oprot.writeString(struct.firstName);
      }
      if (struct.isSetLastName()) {
        oprot.writeString(struct.lastName);
      }
      if (struct.isSetExpMonth()) {
        oprot.writeI32(struct.expMonth);
      }
      if (struct.isSetExpYear()) {
        oprot.writeI32(struct.expYear);
      }
      if (struct.isSetCardNumber()) {
        oprot.writeString(struct.cardNumber);
      }
      if (struct.isSetType()) {
        oprot.writeString(struct.type);
      }
      if (struct.isSetCvc()) {
        oprot.writeString(struct.cvc);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, HCECard struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.firstName = iprot.readString();
        struct.setFirstNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.lastName = iprot.readString();
        struct.setLastNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.expMonth = iprot.readI32();
        struct.setExpMonthIsSet(true);
      }
      if (incoming.get(3)) {
        struct.expYear = iprot.readI32();
        struct.setExpYearIsSet(true);
      }
      if (incoming.get(4)) {
        struct.cardNumber = iprot.readString();
        struct.setCardNumberIsSet(true);
      }
      if (incoming.get(5)) {
        struct.type = iprot.readString();
        struct.setTypeIsSet(true);
      }
      if (incoming.get(6)) {
        struct.cvc = iprot.readString();
        struct.setCvcIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

