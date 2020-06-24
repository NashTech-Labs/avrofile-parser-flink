import org.apache.avro.generic.GenericRecord
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.formats.avro.AvroInputFormat
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala

object FlinkAvroReader extends App {

  val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
  env.setParallelism(1)

  val avroInputFormat = new AvroInputFormat[GenericRecord](new org.apache.flink.core.fs.Path("path-to-avro-file"), classOf[GenericRecord])

  implicit val typeInfo: TypeInformation[GenericRecord] = TypeInformation.of(classOf[GenericRecord])
  val recordStream: scala.DataStream[GenericRecord] = env.createInput(avroInputFormat)

  recordStream.print()
  env.execute(jobName = "flink-avro-demo")
}




