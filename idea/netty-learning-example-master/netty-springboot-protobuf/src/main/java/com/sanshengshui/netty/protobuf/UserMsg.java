// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: user.proto

package com.sanshengshui.netty.protobuf;

public final class UserMsg {
  private UserMsg() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface YLmessageModelOrBuilder extends
      // @@protoc_insertion_point(interface_extends:YLmessageModel)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string textString = 5;</code>
     */
    java.lang.String getTextString();
    /**
     * <code>string textString = 5;</code>
     */
    com.google.protobuf.ByteString
        getTextStringBytes();

    /**
     * <code>string name = 1;</code>
     */
    java.lang.String getName();
    /**
     * <code>string name = 1;</code>
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>int32 messageType = 3;</code>
     */
    int getMessageType();

    /**
     * <code>uint32 voiceLength = 2;</code>
     */
    int getVoiceLength();

    /**
     * <code>bytes voiceData = 4;</code>
     */
    com.google.protobuf.ByteString getVoiceData();
  }
  /**
   * Protobuf type {@code YLmessageModel}
   */
  public  static final class YLmessageModel extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:YLmessageModel)
      YLmessageModelOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use YLmessageModel.newBuilder() to construct.
    private YLmessageModel(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private YLmessageModel() {
      textString_ = "";
      name_ = "";
      messageType_ = 0;
      voiceLength_ = 0;
      voiceData_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private YLmessageModel(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              name_ = s;
              break;
            }
            case 16: {

              voiceLength_ = input.readUInt32();
              break;
            }
            case 24: {

              messageType_ = input.readInt32();
              break;
            }
            case 34: {

              voiceData_ = input.readBytes();
              break;
            }
            case 42: {
              java.lang.String s = input.readStringRequireUtf8();

              textString_ = s;
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.sanshengshui.netty.protobuf.UserMsg.internal_static_YLmessageModel_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.sanshengshui.netty.protobuf.UserMsg.internal_static_YLmessageModel_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel.class, com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel.Builder.class);
    }

    public static final int TEXTSTRING_FIELD_NUMBER = 5;
    private volatile java.lang.Object textString_;
    /**
     * <code>string textString = 5;</code>
     */
    public java.lang.String getTextString() {
      java.lang.Object ref = textString_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        textString_ = s;
        return s;
      }
    }
    /**
     * <code>string textString = 5;</code>
     */
    public com.google.protobuf.ByteString
        getTextStringBytes() {
      java.lang.Object ref = textString_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        textString_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int NAME_FIELD_NUMBER = 1;
    private volatile java.lang.Object name_;
    /**
     * <code>string name = 1;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      }
    }
    /**
     * <code>string name = 1;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int MESSAGETYPE_FIELD_NUMBER = 3;
    private int messageType_;
    /**
     * <code>int32 messageType = 3;</code>
     */
    public int getMessageType() {
      return messageType_;
    }

    public static final int VOICELENGTH_FIELD_NUMBER = 2;
    private int voiceLength_;
    /**
     * <code>uint32 voiceLength = 2;</code>
     */
    public int getVoiceLength() {
      return voiceLength_;
    }

    public static final int VOICEDATA_FIELD_NUMBER = 4;
    private com.google.protobuf.ByteString voiceData_;
    /**
     * <code>bytes voiceData = 4;</code>
     */
    public com.google.protobuf.ByteString getVoiceData() {
      return voiceData_;
    }

    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getNameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
      }
      if (voiceLength_ != 0) {
        output.writeUInt32(2, voiceLength_);
      }
      if (messageType_ != 0) {
        output.writeInt32(3, messageType_);
      }
      if (!voiceData_.isEmpty()) {
        output.writeBytes(4, voiceData_);
      }
      if (!getTextStringBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 5, textString_);
      }
      unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getNameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
      }
      if (voiceLength_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeUInt32Size(2, voiceLength_);
      }
      if (messageType_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, messageType_);
      }
      if (!voiceData_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(4, voiceData_);
      }
      if (!getTextStringBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, textString_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel)) {
        return super.equals(obj);
      }
      com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel other = (com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel) obj;

      boolean result = true;
      result = result && getTextString()
          .equals(other.getTextString());
      result = result && getName()
          .equals(other.getName());
      result = result && (getMessageType()
          == other.getMessageType());
      result = result && (getVoiceLength()
          == other.getVoiceLength());
      result = result && getVoiceData()
          .equals(other.getVoiceData());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + TEXTSTRING_FIELD_NUMBER;
      hash = (53 * hash) + getTextString().hashCode();
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
      hash = (37 * hash) + MESSAGETYPE_FIELD_NUMBER;
      hash = (53 * hash) + getMessageType();
      hash = (37 * hash) + VOICELENGTH_FIELD_NUMBER;
      hash = (53 * hash) + getVoiceLength();
      hash = (37 * hash) + VOICEDATA_FIELD_NUMBER;
      hash = (53 * hash) + getVoiceData().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code YLmessageModel}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:YLmessageModel)
        com.sanshengshui.netty.protobuf.UserMsg.YLmessageModelOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.sanshengshui.netty.protobuf.UserMsg.internal_static_YLmessageModel_descriptor;
      }

      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.sanshengshui.netty.protobuf.UserMsg.internal_static_YLmessageModel_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel.class, com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel.Builder.class);
      }

      // Construct using com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      public Builder clear() {
        super.clear();
        textString_ = "";

        name_ = "";

        messageType_ = 0;

        voiceLength_ = 0;

        voiceData_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.sanshengshui.netty.protobuf.UserMsg.internal_static_YLmessageModel_descriptor;
      }

      public com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel getDefaultInstanceForType() {
        return com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel.getDefaultInstance();
      }

      public com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel build() {
        com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel buildPartial() {
        com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel result = new com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel(this);
        result.textString_ = textString_;
        result.name_ = name_;
        result.messageType_ = messageType_;
        result.voiceLength_ = voiceLength_;
        result.voiceData_ = voiceData_;
        onBuilt();
        return result;
      }

      public Builder clone() {
        return (Builder) super.clone();
      }
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel) {
          return mergeFrom((com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel other) {
        if (other == com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel.getDefaultInstance()) return this;
        if (!other.getTextString().isEmpty()) {
          textString_ = other.textString_;
          onChanged();
        }
        if (!other.getName().isEmpty()) {
          name_ = other.name_;
          onChanged();
        }
        if (other.getMessageType() != 0) {
          setMessageType(other.getMessageType());
        }
        if (other.getVoiceLength() != 0) {
          setVoiceLength(other.getVoiceLength());
        }
        if (other.getVoiceData() != com.google.protobuf.ByteString.EMPTY) {
          setVoiceData(other.getVoiceData());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object textString_ = "";
      /**
       * <code>string textString = 5;</code>
       */
      public java.lang.String getTextString() {
        java.lang.Object ref = textString_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          textString_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string textString = 5;</code>
       */
      public com.google.protobuf.ByteString
          getTextStringBytes() {
        java.lang.Object ref = textString_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          textString_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string textString = 5;</code>
       */
      public Builder setTextString(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        textString_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string textString = 5;</code>
       */
      public Builder clearTextString() {
        
        textString_ = getDefaultInstance().getTextString();
        onChanged();
        return this;
      }
      /**
       * <code>string textString = 5;</code>
       */
      public Builder setTextStringBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        textString_ = value;
        onChanged();
        return this;
      }

      private java.lang.Object name_ = "";
      /**
       * <code>string name = 1;</code>
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string name = 1;</code>
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string name = 1;</code>
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string name = 1;</code>
       */
      public Builder clearName() {
        
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>string name = 1;</code>
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        name_ = value;
        onChanged();
        return this;
      }

      private int messageType_ ;
      /**
       * <code>int32 messageType = 3;</code>
       */
      public int getMessageType() {
        return messageType_;
      }
      /**
       * <code>int32 messageType = 3;</code>
       */
      public Builder setMessageType(int value) {
        
        messageType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 messageType = 3;</code>
       */
      public Builder clearMessageType() {
        
        messageType_ = 0;
        onChanged();
        return this;
      }

      private int voiceLength_ ;
      /**
       * <code>uint32 voiceLength = 2;</code>
       */
      public int getVoiceLength() {
        return voiceLength_;
      }
      /**
       * <code>uint32 voiceLength = 2;</code>
       */
      public Builder setVoiceLength(int value) {
        
        voiceLength_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>uint32 voiceLength = 2;</code>
       */
      public Builder clearVoiceLength() {
        
        voiceLength_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString voiceData_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes voiceData = 4;</code>
       */
      public com.google.protobuf.ByteString getVoiceData() {
        return voiceData_;
      }
      /**
       * <code>bytes voiceData = 4;</code>
       */
      public Builder setVoiceData(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        voiceData_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bytes voiceData = 4;</code>
       */
      public Builder clearVoiceData() {
        
        voiceData_ = getDefaultInstance().getVoiceData();
        onChanged();
        return this;
      }
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:YLmessageModel)
    }

    // @@protoc_insertion_point(class_scope:YLmessageModel)
    private static final com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel();
    }

    public static com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<YLmessageModel>
        PARSER = new com.google.protobuf.AbstractParser<YLmessageModel>() {
      public YLmessageModel parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new YLmessageModel(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<YLmessageModel> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<YLmessageModel> getParserForType() {
      return PARSER;
    }

    public com.sanshengshui.netty.protobuf.UserMsg.YLmessageModel getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_YLmessageModel_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_YLmessageModel_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nuser.proto\"o\n\016YLmessageModel\022\022\n\ntextSt" +
      "ring\030\005 \001(\t\022\014\n\004name\030\001 \001(\t\022\023\n\013messageType\030" +
      "\003 \001(\005\022\023\n\013voiceLength\030\002 \001(\r\022\021\n\tvoiceData\030" +
      "\004 \001(\014B*\n\037com.sanshengshui.netty.protobuf" +
      "B\007UserMsgb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_YLmessageModel_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_YLmessageModel_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_YLmessageModel_descriptor,
        new java.lang.String[] { "TextString", "Name", "MessageType", "VoiceLength", "VoiceData", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
