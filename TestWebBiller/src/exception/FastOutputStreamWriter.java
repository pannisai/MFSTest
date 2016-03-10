package exception;

import java.io.Writer;

public class FastOutputStreamWriter extends Writer {
	byte bb[];
	int size;
	int count;
	int stackLevel;
	@SuppressWarnings("unused")
	private final ReportServiceException this$0; /* synthetic field */

	FastOutputStreamWriter(ReportServiceException fwsexception) {
		this$0 = fwsexception;
		bb = new byte[128];
		size = 128;
		count = 0;
		stackLevel = 0;
	}

	public void write(char ac[], int i, int j) {
		for(; j + count > size; size += 128) {
			byte abyte0[] = new byte[bb.length + 128];
			System.arraycopy(bb, 0, abyte0, 0, count);
			bb = abyte0;
		}

		for(int k = 0; k < j; k++)
			if(stackLevel < 1) {
				bb[count++] = (byte)ac[i + k];
				if(ac[i + k] == ')') {
					stackLevel++;
					bb[count++] = 0;
				}
			}

	}

	public void flush() {
	}

	public void close() {
	}

	public byte[] getByteArray() {
		return bb;
	}

	public int size() {
		return bb.length;
	}
}
