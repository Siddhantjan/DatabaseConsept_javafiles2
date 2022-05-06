package com.personal.javafilestwo.vertex.core.buffers;

import io.vertx.core.buffer.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BufferExampler {
    private static final Logger LOG = LoggerFactory.getLogger(BufferExampler.class);
    /**  A buffer is a sequence of zero or more bytes that can read from or written to and
       which expands automatically as necessary to accommodate any bytes written to it.
       You can perhaps think of a buffer as smart byte array.
     */
    public static void main(String[] args) {
        // TODO : Create a empty buffer
        Buffer buffer = Buffer.buffer();
     //   buffer.appendInt(1);
       //Printing of particular pos
       // LOG.debug("Buffer message :"+buffer.getInt(0));
        buffer.setInt(1000, 123);
        buffer.setString(0, "hello");
        LOG.debug("Buffer Message for Buffer "+buffer.getInt(1000));
        Buffer buff = Buffer.buffer("some string");
        LOG.debug("Buffer Message of Buff :"+buff.getString(0, buff.length()));

        Buffer buf = Buffer.buffer("some string", "UTF-16");
        LOG.debug("Buffer Message of Buf :"+buf);


        Buffer bufff = Buffer.buffer(128);
        int pos = 15;
        bufff.setUnsignedByte(pos, (short) 200);
        System.out.println(bufff.getUnsignedByte(pos));
    }

}
