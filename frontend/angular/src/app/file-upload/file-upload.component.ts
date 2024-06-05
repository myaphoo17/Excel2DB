import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent {

  constructor(private http: HttpClient) {}

  onFileChange(event: any) {
    this.uploadFile(event);
  }

  uploadFile(event: any) {
    const file = event.target.files[0];
    const formData = new FormData();
    formData.append('file', file);

    this.http.post('http://localhost:8080/api/excel/upload', formData)
      .subscribe(
        (response) => {
          console.log('File uploaded successfully', response);
        },
        (error: HttpErrorResponse) => {
          console.error('Error uploading file', error);
          if (error.status === 400) {
            alert('Invalid file format. Please upload an Excel file.');
          } else if (error.status === 500) {
            alert('An error occurred on the server. Please try again later.');
          } else {
            alert('An unexpected error occurred. Please try again.');
          }
        }
      );
  }
}
