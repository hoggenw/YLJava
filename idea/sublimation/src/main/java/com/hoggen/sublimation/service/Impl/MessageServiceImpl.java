package com.hoggen.sublimation.service.Impl;

import com.hoggen.sublimation.enums.CmdEnum;
import com.hoggen.sublimation.enums.ModuleEnum;
import com.hoggen.sublimation.enums.SocketTitle;
import com.hoggen.sublimation.proto.BaseMsg;
import com.hoggen.sublimation.proto.UserMsg;
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
            UserMsg.YLmessageModel model = UserMsg.YLmessageModel.parseFrom(bytes);
            System.out.println("  姓名  " + model.getName() + "  内容  "+ model.getTextString());

            UserMsg.YLmessageModel.Builder builder =  UserMsg.YLmessageModel.newBuilder();
            builder.setTextString("我收到你的信息了").setMessageType(2);
            UserMsg.YLmessageModel model2 = builder.build();

            BaseMsg.YLBaseMessageModel.Builder baseBuilder = BaseMsg.YLBaseMessageModel.newBuilder();
            baseBuilder.setTitle(SocketTitle.SocketTitle).setModule(ModuleEnum.COMMON_MODULE).setCommand(CmdEnum.PERSON_TO_PERSON).setData(model2.toByteString());

            ByteBuf buf2 = Unpooled.wrappedBuffer(baseBuilder.build().toByteArray());
            return buf2;

        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }



    }
}
