import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';

import * as bcrypt from 'bcrypt';

@Injectable()
export class SecurityService {
  constructor(private readonly jwtService: JwtService) {}

  async validatePassword(password: string, hashedPassword): Promise<boolean> {
    return await bcrypt.compare(password, hashedPassword);
  }

  async hashPassword(password: string): Promise<string> {
    const salt = await bcrypt.genSalt();
    return await bcrypt.hash(password, salt);
  }

  async generateToken(): Promise<{ accessToken: string; expiresIn: number }> {
    const expiresIn = 3600;
    const token = await this.jwtService.sign({}, { expiresIn });

    return { accessToken: token, expiresIn };
  }
}
