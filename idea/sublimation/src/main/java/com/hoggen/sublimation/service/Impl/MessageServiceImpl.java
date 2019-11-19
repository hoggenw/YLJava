package com.hoggen.sublimation.service.Impl;

import com.hoggen.sublimation.enums.CmdEnum;
import com.hoggen.sublimation.enums.ModuleEnum;
import com.hoggen.sublimation.enums.SocketTitle;
import com.hoggen.sublimation.proto.BaseMessageModel;
import com.hoggen.sublimation.proto.MessageModel;
import com.hoggen.sublimation.service.MessageService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.stereotype.Component;


@Component
public class MessageServiceImpl implements MessageService {

    @Override
    public Object messageSendToPerson(com.google.protobuf.ByteString bytes) {
        System.out.println("person to person");
        //System.out.println("======线程22====" + Thread.currentThread().getName());

        try {
            MessageModel.YLMessageModel model = MessageModel.YLMessageModel.parseFrom(bytes);
            System.out.println("  userid  " + model.getFromUser().getUserId() + "  内容  "+ model.getTextString());

            MessageModel.YLMessageModel.Builder builder =  MessageModel.YLMessageModel.newBuilder();
            builder.setTextString("我收到你的信息了").setMessageType(2);
            MessageModel.YLMessageModel model2 = builder.build();

            BaseMessageModel.YLBaseMessageModel.Builder baseBuilder = BaseMessageModel.YLBaseMessageModel.newBuilder();
            baseBuilder.setTitle(SocketTitle.SocketTitle).setModule(ModuleEnum.COMMON_MODULE).setCommand(CmdEnum.PERSON_TO_PERSON).setData(model2.toByteString());

            ByteBuf buf2 = Unpooled.wrappedBuffer(baseBuilder.build().toByteArray());
            return buf2;

        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }



    }
}
