export interface Chapter{
    id: string;
    chapterTitle: string;
    content: string;
    lastModified: Date;
    bookId: string;
}

export interface AddChapterRequest{
    chapterTitle: string;
    content: string;
    lastModified: Date;
    bookId: string;
}

export interface EditChapterRequest{
    id: string;
    chapterTitle: string;
    content: string;
    lastModified: Date;
    bookId: string;
}
