# NoteApp - Command Line Note Taking Application

## 📖 Mô tả
NoteApp là một ứng dụng ghi chú đơn giản được phát triển bằng Java. Ứng dụng này chạy trên giao diện dòng lệnh (CLI) và cho phép người dùng tạo, sửa, xóa và tìm kiếm các ghi chú. Ứng dụng cũng hỗ trợ tính năng đa người dùng giả lập để ghi lại các log hoạt động.

## 🚀 Các tính năng chính

1. **Thêm ghi chú (Add Note)**: Người dùng có thể nhập ghi chú mới và lưu lại vào danh sách ghi chú.
2. **Xem ghi chú dưới định dạng JSON (View Notes in JSON format)**: Hiển thị tất cả các ghi chú dưới dạng JSON, dễ dàng xem và xử lý.
3. **Mô phỏng hoạt động của nhiều người dùng (Simulate Multi-user Logging)**: Mô phỏng các hoạt động ghi chú của hai người dùng khác nhau đồng thời và ghi lại log.
4. **Xóa ghi chú (Delete Note)**: Cho phép người dùng xóa một ghi chú cụ thể khỏi danh sách.
5. **Chỉnh sửa ghi chú (Edit Note)**: Người dùng có thể chỉnh sửa nội dung ghi chú đã tạo.
6. **Xem chi tiết ghi chú (View Note Details)**: Hiển thị thông tin chi tiết của một ghi chú, bao gồm nội dung và thời gian tạo.
7. **Tìm kiếm ghi chú (Search Notes)**: Tìm kiếm ghi chú theo nội dung hoặc theo ngày tháng.
8. **Thoát ứng dụng (Exit)**: Thoát khỏi ứng dụng và lưu lại tất cả ghi chú vào file JSON.

## 🛠️ Cài đặt

Để sử dụng ứng dụng, bạn cần cài đặt Java (JDK) trên máy tính của mình. Hãy chắc chắn rằng bạn đã cài đặt phiên bản Java 11 hoặc cao hơn.

### 1. Cài đặt Java
- Tải và cài đặt JDK từ [Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) hoặc [OpenJDK](https://adoptopenjdk.net/).
- Cấu hình biến môi trường `JAVA_HOME`.

### 2. Clone repository về máy
Mở terminal và clone repository về máy bằng lệnh sau:

```bash
git clone https://github.com/Quangdv99/NoteApp.git
cd NoteApp


Cấu trúc thư mục

NoteApp/
│
├── src/
│   ├── Main/
│   │   └── NoteApp.java       # Mã nguồn chính của ứng dụng
│   └── models/
│       └── Note.java          # Lớp Note dùng để lưu trữ nội dung ghi chú
├── README.md                  # Tài liệu hướng dẫn sử dụng
└── notes.json                  # File lưu trữ dữ liệu ghi chú dưới định dạng JSON


🔧 Cách sử dụng
Sau khi chạy ứng dụng, bạn sẽ thấy một menu CLI với các lựa chọn sau:

Add Note: Thêm ghi chú mới.

View Notes (JSON format): Xem tất cả ghi chú dưới định dạng JSON.

Simulate Multi-user Logging: Mô phỏng hoạt động của hai người dùng đồng thời.

Delete Note: Xóa một ghi chú.

Edit Note: Chỉnh sửa một ghi chú.

View Note Details: Xem chi tiết của một ghi chú.

Search Notes: Tìm kiếm ghi chú theo nội dung hoặc ngày.

Exit: Thoát khỏi ứng dụng.

⚙️ Lưu trữ Dữ liệu
Dữ liệu ghi chú sẽ được lưu vào file notes.json trong thư mục hiện tại. Mỗi ghi chú sẽ được lưu với các thông tin như: nội dung ghi chú và thời gian tạo.

📄 Các phương thức lưu trữ
Ứng dụng sẽ lưu trữ các ghi chú trong file notes.json dưới định dạng JSON sau mỗi lần thay đổi.

{
  "notes": [
    {
      "content": "This is my first note.",
      "createdAt": "2025-04-26T13:20:00"
    }
  ]
}


📝 Ghi chú về cách sử dụng
Xử lý lỗi đầu vào: Ứng dụng sẽ kiểm tra và yêu cầu người dùng nhập đúng giá trị khi chọn các thao tác.

Log đa người dùng: Tính năng giả lập nhiều người dùng giúp bạn thấy các hoạt động đồng thời được ghi lại.

🎓 Đóng góp
Nếu bạn muốn đóng góp cho dự án này, vui lòng tạo một pull request hoặc báo cáo lỗi thông qua Issues.
